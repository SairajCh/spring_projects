package in.ies.service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ies.bindings.DashboardCard;
import in.ies.bindings.LoginForm;
import in.ies.bindings.UserAccountForm;
import in.ies.constants.AppConstants;
import in.ies.entities.EligEntity;
import in.ies.entities.UserEntity;
import in.ies.repositories.EligRepo;
import in.ies.repositories.PlanRepo;
import in.ies.repositories.UserRepo;
import in.ies.utils.EmailUtils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	@Autowired
	private EmailUtils emailUtils;

	@Autowired
	private PlanRepo planRepo;

	@Autowired
	private EligRepo eligRepo;

	@Override
	public String login(LoginForm loginForm) {

		UserEntity userEntity = userRepo.findByEmailAndPwd(loginForm.getEmail(), loginForm.getPwd());

		if (userEntity == null) {
			return AppConstants.INVALID_CRED;
		}

		if (AppConstants.Y_STR.equals(userEntity.getActiveSw()) && AppConstants.UNLOCKED.equals(userEntity.getAccStatus())) {

			return "success@role" + userEntity.getRoleId();

		} else {
			return AppConstants.ACC_LOCKED;
		}
	}

	@Override
	public boolean recoverPassword(String email) {
		UserEntity userEntity = userRepo.findByEmail(email);
		if (null == userEntity) {
			return false;
		} else {

			String subject = AppConstants.RECOVER_SUB;
			String body = readEmailBody(AppConstants.PWD_BODY_FILE, userEntity);
			return emailUtils.sendEmail(subject, body, email);
		}

	}

	@Override
	public UserAccountForm getUserByEmail(String email) {

		UserEntity userEntity = userRepo.findByEmail(email);
		UserAccountForm user = new UserAccountForm();

		BeanUtils.copyProperties(userEntity, user);

		return user;
	}

	@Override
	public DashboardCard fetchDashboardInfo() {

		long plansCount = planRepo.count();

		List<EligEntity> eligList = eligRepo.findAll();

		long approvedCnt = eligList.stream().filter(ed -> ed.getPlanStatus().equals(AppConstants.AP)).count();

		long deniedCnt = eligList.stream().filter(ed -> ed.getPlanStatus().equals(AppConstants.DN)).count();

		Double total = eligList.stream().mapToDouble(ed -> ed.getBenefitAmt()).summaryStatistics().getSum();

		DashboardCard card = new DashboardCard();
		card.setPlansCnt(plansCount);
		card.setApprovedCnt(0l);
		card.setDeniedCnt(0l);
		card.setBeniftAmtGiven(total);

		return card;
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
