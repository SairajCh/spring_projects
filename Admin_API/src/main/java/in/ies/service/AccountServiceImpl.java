package in.ies.service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ies.bindings.UnlockAccForm;
import in.ies.bindings.UserAccountForm;
import in.ies.constants.AppConstants;
import in.ies.entities.UserEntity;
import in.ies.repositories.UserRepo;
import in.ies.utils.EmailUtils;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private EmailUtils emailUtils;

	@Override
	public Boolean createUserAccount(UserAccountForm accForm) {

		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(accForm, userEntity);

		// set random password

		userEntity.setPwd(generatePwd());

		// set account status
		userEntity.setAccStatus(AppConstants.LOCKED);
		userEntity.setActiveSw(AppConstants.Y_STR);

		userRepo.save(userEntity);

		// send email

		String subject = " ";
		String body = "";

		return emailUtils.sendEmail(subject, body, accForm.getEmail());

	}

	@Override
	public List<UserAccountForm> fetchUserAccounts() {

		List<UserEntity> userEntities = userRepo.findAll();
		List<UserAccountForm> users = new ArrayList<>();

		for (UserEntity userEntity : userEntities) {
			UserAccountForm user = new UserAccountForm();
			BeanUtils.copyProperties(userEntity, user);
			users.add(user);
		}

		return users;
	}

	@Override
	public UserAccountForm getUserAccById(Integer accId) {

		Optional<UserEntity> optional = userRepo.findById(accId);

		if (optional.isPresent()) {
			UserEntity userEntity = optional.get();

			UserAccountForm user = new UserAccountForm();
			BeanUtils.copyProperties(userEntity, user);
			return user;

		}
		return null;

	}

	@Override
	public String changeAccStatus(Integer userId, String status) {

		Integer cnt = userRepo.updateAccStatus(userId, status);
		if (cnt > 0) {
			return "Status Changed";
		}

		return "Failed To Change";
	}

	@Override
	public String unlockUserAccount(UnlockAccForm unlockAccForm) {

		UserEntity userEntity = userRepo.findByEmail(unlockAccForm.getEmail());

		userEntity.setPwd(unlockAccForm.getNewPwd());
		userEntity.setAccStatus(AppConstants.UNLOCKED);

		userRepo.save(userEntity);

		return "Account Unlocked";
	}

	private String generatePwd() {

		String upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String lowerAlphabet = "abcdefghijklmnopqrstuvwxyz";
		String numbers = "0123456789";

		// combine all strings
		String alphaNumeric = upperAlphabet + lowerAlphabet + numbers;

		// create random string builder
		StringBuilder sb = new StringBuilder();

		// create an object of Random class
		Random random = new Random();

		// specify length of random string
		int length = 6;

		for (int i = 0; i < length; i++) {

			// generate random index number
			int index = random.nextInt(alphaNumeric.length());

			// get character specified by index
			// from the string
			char randomChar = alphaNumeric.charAt(index);

			// append the character to string builder
			sb.append(randomChar);
		}

		return sb.toString();

	}

	private String readEmailBody(String filename, UserEntity user) {

		StringBuilder sb = new StringBuilder();

		try (Stream<String> lines = Files.lines(Paths.get(filename))) {

			lines.forEach(line -> {
				line = line.replace(AppConstants.FNAME, user.getFullName());
				line = line.replace(AppConstants.PWD, user.getPwd());
				line = line.replace(AppConstants.EMAIL, user.getEmail()); 

				sb.append(line);
			});

		} catch (Exception e) {
			e.printStackTrace();
		}

		return sb.toString();

	}

}
