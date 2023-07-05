package in.ashokit.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.binding.LoginForm;
import in.ashokit.binding.SignUpForm;
import in.ashokit.binding.UnlockForm;
import in.ashokit.constants.AppConstants;
import in.ashokit.entity.UserDtlsEntity;
import in.ashokit.repo.UserDtlsRepo;
import in.ashokit.utility.EmailUtils;
import in.ashokit.utility.PwdUtils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDtlsRepo userDtlsRepo;

	@Autowired
	private EmailUtils emailUtils;

	@Autowired
	private HttpSession session;
	
	@Override
	public boolean signUp(SignUpForm form) {

		UserDtlsEntity user = userDtlsRepo.findByEmail(form.getEmail());

		if (user != null) {
			return false;
		}

		// TODO: Copy data from binding obj to entity obj

		UserDtlsEntity entity = new UserDtlsEntity();
		BeanUtils.copyProperties(form, entity);

		// TODO generate the random pwd

		String tempPwd = PwdUtils.generateRandomPwd();
		entity.setPwd(tempPwd);

		// TODO: Set account status locked
		entity.setAccStatus(AppConstants.STR_LOCKED);

		// TODO: Insert record
		userDtlsRepo.save(entity);

		// TODO: Send email to unlock the account
		String to = form.getEmail();
		String subject = AppConstants.UNLOCK_EMAIL_SUBJECT;
		StringBuilder body = new StringBuilder("");

		body.append("<h1>Use below temporaray pwd to unlock your account </h1>");

		body.append("Temporary password)" + tempPwd);

		body.append("<br/>");
		body.append("<a href=\"http://localhost:8081/unlock?email=" + to + "\">Click here to unlock account</a>");

		emailUtils.sendEmail(to, subject, body.toString());

		return true;
	}

	@Override
	public boolean unlockAccount(UnlockForm form) {
		

		UserDtlsEntity entity = userDtlsRepo.findByEmail(form.getEmail());
		if (entity.getPwd().equals(form.getTempPwd())) {

			entity.setPwd(form.getNewPwd());
			entity.setAccStatus(AppConstants.STR_UNLOCKED);
			userDtlsRepo.save(entity);

			return true;
		} else {
			return false;
		}

	}

	@Override
	public String login(LoginForm form) {
		// TODO Auto-generated method stub



		UserDtlsEntity entity = userDtlsRepo.findByEmailAndPwd(form.getEmail(), form.getPwd());
		if (entity == null) {

			return AppConstants.INVALID_CREDENTIALS_MSG;
		}
		if (entity.getAccStatus().equals("LOCKED")) {
			return AppConstants.STR_ACCOUNT_LOCKED_MSG;
		}
		
		session.setAttribute(AppConstants.STR_USER_ID, entity.getUserId());

		return AppConstants.STR_SUCCESS;
	}

	@Override
	public String forgotPwd(String email) {
		
		UserDtlsEntity entity = userDtlsRepo.findByEmail(email);
		//check record presence in db with given email
		
		//if record not available send error msg
		
		if(entity==null) {
			return "Invalid email id";
		}
		
		//if record available send pwd to email and send succs msg
		
		
		String subject=AppConstants.RECOVER_PZZWD_EMAIL_SUBJECT;
		String body="Your password::"+entity.getPwd();
		
		emailUtils.sendEmail(email, subject,body);
		
		return "Password sent to your mail";
	}

}
