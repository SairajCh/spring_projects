package in.ashokit.service;

import in.ashokit.binding.LoginForm;
import in.ashokit.binding.UnlockForm;

public interface IesUserService{
	
	public String login(LoginForm loginForm);
	
	public String forgotPwd(String email);
	
	public boolean unlockAccount(UnlockForm unlockForm);

}
