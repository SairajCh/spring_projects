package in.ashokit.service;

import in.ashokit.binding.ForgotPwdForm;
import in.ashokit.binding.LoginForm;
import in.ashokit.binding.SignupForm;

public interface UserService {

	public Boolean signUp(SignupForm form);
	
	public Boolean login(LoginForm form);
	
	public Boolean forgotPwd(ForgotPwdForm form);
	
}
