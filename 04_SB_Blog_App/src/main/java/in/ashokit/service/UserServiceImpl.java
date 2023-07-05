package in.ashokit.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.binding.ForgotPwdForm;
import in.ashokit.binding.LoginForm;
import in.ashokit.binding.SignupForm;
import in.ashokit.entity.User;
import in.ashokit.repo.UserRepo;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private HttpSession session;

	@Override
	public Boolean signUp(SignupForm form) {
		// TODO Auto-generated method stub
	User userEntity=new User();
	
	BeanUtils.copyProperties(form, userEntity);
	userRepo.save(userEntity);
	
	
		
		
		return true;
	}

	@Override
	public Boolean login(LoginForm form) {
		// TODO Auto-generated method stub
		User userEntity = userRepo.findByEmailAndPwd(form.getEmail(), form.getPwd());
		
		if(userEntity==null) {
			return false;
			
		}
	
		session.setAttribute("userId", userEntity.getUserId());	
		
		return true;
	}

	@Override
	public Boolean forgotPwd(ForgotPwdForm form) {
		// TODO Auto-generated method stub
		return null;
	}

}
