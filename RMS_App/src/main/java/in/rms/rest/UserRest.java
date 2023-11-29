package in.rms.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.rms.constants.RmsConstants;
import in.rms.service.UserService;
import in.rms.utils.RmsUtils;
import in.rms.wrapper.UserWrapper;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j

@RequestMapping(path = "/user")
public class UserRest {

	@Autowired
	private UserService userService;

	@PostMapping("/signup")
	public ResponseEntity<String> signUp(@RequestBody(required = true) Map<String, String> requestMap) {

		try {
			return userService.signUp(requestMap);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return RmsUtils.getResponseEntity(RmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody(required = true) Map<String, String> requestMap) {

		try {
			return userService.login(requestMap);

		} catch (Exception e) {
			e.printStackTrace();

		}

		return RmsUtils.getResponseEntity(RmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@GetMapping("/get")

	public ResponseEntity<List<UserWrapper>> getAllUser() {

		try {
			return userService.getAllUser();

		} catch (Exception ex) {
			log.error("exception in rest get method");
			ex.printStackTrace();
		}

		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@PostMapping("/update")
	public ResponseEntity<String> update(@RequestBody Map<String, String> requestMap) {

		try {
			return userService.update(requestMap);
		} catch (Exception e) {
			log.error("exception in rest update method");
			e.printStackTrace();
		}

		return RmsUtils.getResponseEntity(RmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@GetMapping("/checkToken")

	public ResponseEntity<String> checkToken() {

		try {

			return userService.checkToken();

		} catch (Exception ex) {
			ex.printStackTrace();

		}

		return RmsUtils.getResponseEntity(RmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@PostMapping("/changePassword")
	
	public ResponseEntity<String> changePassword(@RequestBody Map<String,String> requestMap){
		try {
			return userService.changePassword(requestMap);
		} catch (Exception e) {
			
		}
		return RmsUtils.getResponseEntity(RmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PostMapping("/forgotPassword")
	public ResponseEntity<String> forgotPassword(@RequestBody Map<String,String> requestMap){
		
		try {
			return userService.forgotPassword(requestMap);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return RmsUtils.getResponseEntity(RmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
		
	

}
