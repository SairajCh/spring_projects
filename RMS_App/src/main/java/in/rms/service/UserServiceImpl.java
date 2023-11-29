package in.rms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.google.common.base.Objects;
import com.google.common.base.Strings;

import in.rms.constants.RmsConstants;
import in.rms.entity.User;
import in.rms.jwt.CustomerUserDetailsService;
import in.rms.jwt.JwtFilter;
import in.rms.jwt.JwtUtil;
import in.rms.repository.UserRepo;
import in.rms.utils.EmailUtils;
import in.rms.utils.RmsUtils;
import in.rms.wrapper.UserWrapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomerUserDetailsService customerUserDetailsService;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private JwtFilter jwtFilter;

	@Autowired
	private EmailUtils emailUtils;

	public ResponseEntity<String> signUp(Map<String, String> requestMap) {
		// TODO Auto-generated method stub

		log.info("Inside signup{}", requestMap);
		try {

			if (validateSignUpMap(requestMap)) {

				User user = userRepo.findByEmail(requestMap.get("email"));

				if (user == null) {
					userRepo.save(getUserFromMap(requestMap));
					return RmsUtils.getResponseEntity("Successfully registered", HttpStatus.OK);
				} else {
					return RmsUtils.getResponseEntity("Email already exists", HttpStatus.BAD_REQUEST);
				}

			} else {
				return RmsUtils.getResponseEntity(RmsConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			e.printStackTrace();

			return RmsUtils.getResponseEntity(RmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}

	private boolean validateSignUpMap(Map<String, String> requestMap) {
		if (requestMap.containsKey("name") && requestMap.containsKey("contactNumber") && requestMap.containsKey("email")
				&& requestMap.containsKey("password")) {
			return true;
		}

		return false;
	}

	private User getUserFromMap(Map<String, String> requestMap) {

		User user = new User();
		user.setName(requestMap.get("name"));
		user.setContactNumber(requestMap.get("contactNumber"));
		user.setEmail(requestMap.get("email"));
		user.setPassword(requestMap.get("password"));
		user.setRole("user");
		user.setStatus("false");

		return user;

	}

	@Override
	public ResponseEntity<String> login(Map<String, String> requestMap) {
		log.info("Inside login");

		try {
			log.info("authentication begin");
			Authentication auth = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(requestMap.get("email"), requestMap.get("password")));

			log.info("authentication end");

			if (auth.isAuthenticated()) {

				if (customerUserDetailsService.getUserDetail().getStatus().equalsIgnoreCase("true")) {

					return new ResponseEntity<String>(
							"{\"token\":" + jwtUtil.generateToken(customerUserDetailsService.getUserDetail().getEmail(),
									customerUserDetailsService.getUserDetail().getRole()) + "\"}",
							HttpStatus.OK);
				} else {

					return new ResponseEntity<String>(String.format("{\"message\":\"%s}", "waiting for admin approval"),
							HttpStatus.BAD_REQUEST);
				}

			}
		} catch (Exception e) {

			log.error("{}", e);

		}
		return new ResponseEntity<String>(String.format("{\"message\":\"%s}", "Bad credentials"),
				HttpStatus.BAD_REQUEST);

	}

	@Override
	public ResponseEntity<List<UserWrapper>> getAllUser() {
		try {
			if (jwtFilter.isAdmin()) {

				List<UserWrapper> userWrappers = new ArrayList<>();

				List<User> allUsers = userRepo.getAllUsers();

				for (User user : allUsers) {
					UserWrapper userWrapper = new UserWrapper();

					BeanUtils.copyProperties(user, userWrapper);
					userWrappers.add(userWrapper);

				}
				return new ResponseEntity<>(userWrappers, HttpStatus.OK);

			} else {
				return new ResponseEntity<>(new ArrayList<>(), HttpStatus.UNAUTHORIZED);
			}

		} catch (Exception e) {
			e.printStackTrace();

		}

		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> update(Map<String, String> requestMap) {

		try {

			if (jwtFilter.isAdmin()) {

				Optional<User> optional = userRepo.findById(Integer.parseInt(requestMap.get("id")));

				if (optional.isPresent()) {
					userRepo.updateStatus(requestMap.get("status"), Integer.parseInt(requestMap.get("id")));

					sendMailToAllAdmin(requestMap.get("status"), optional.get().getEmail(), userRepo.getAllAdmin());

					return RmsUtils.getResponseEntity("User Status updated successfully", HttpStatus.OK);

				} else {
					RmsUtils.getResponseEntity("User id does not exist", HttpStatus.OK);
				}

			} else {
				RmsUtils.getResponseEntity(RmsConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
			}

		} catch (Exception ex) {
			ex.printStackTrace();

		}

		return RmsUtils.getResponseEntity(RmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private void sendMailToAllAdmin(String status, String user, List<String> allAdmin) {
		allAdmin.remove(jwtFilter.getCurrentUser());

		if (status != null && status.equalsIgnoreCase("true")) {
			emailUtils.sendSimpleMessage(jwtFilter.getCurrentUser(), "Account Approved",
					"User:" + user + "\n is approved by \n Admin:" + jwtFilter.getCurrentUser(), allAdmin);

		} else {

			emailUtils.sendSimpleMessage(jwtFilter.getCurrentUser(), "Account Disabled",
					"User:" + user + "\n is disabled by \n Admin:" + jwtFilter.getCurrentUser(), allAdmin);

		}

	}

	@Override
	public ResponseEntity<String> checkToken() {
		return RmsUtils.getResponseEntity("true", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> changePassword(Map<String, String> requestMap) {

		try {
			User userObj = userRepo.findByEmail(jwtFilter.getCurrentUser());
			if(userObj!=null) {
				
				if(userObj.getPassword().equals(requestMap.get("oldPassword"))) {
					userObj.setPassword(requestMap.get("newPassword"));
					userRepo.save(userObj);
					return RmsUtils.getResponseEntity("Password Updated Succesfully", HttpStatus.OK);
				}
				return RmsUtils.getResponseEntity("Incorrect Old Password", HttpStatus.BAD_REQUEST);
				
			}
			return RmsUtils.getResponseEntity(RmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
			
			
			
			

		} catch (Exception e) {
			e.printStackTrace();
		}

		return RmsUtils.getResponseEntity(RmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@Override
	public ResponseEntity<String> forgotPassword(Map<String, String> requestMap) {
		try {
			
			User user = userRepo.findByEmail(requestMap.get("email"));
			
			if(user!=null && !Strings.isNullOrEmpty(user.getEmail())) 
				emailUtils.forgotMail(user.getEmail(), "Credentials from RMS System", user.getPassword());
				
				
				
				return RmsUtils.getResponseEntity("Check your mail for Credentials", HttpStatus.OK);
				
			
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		
		return RmsUtils.getResponseEntity(RmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
