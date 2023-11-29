package in.ies.service;

import in.ies.binding.LoginForm;
import in.ies.binding.UnlockForm;

public interface IesUserService{
	
	public String login(LoginForm loginForm);
	
	public String forgotPwd(String email);
	
	public boolean unlockAccount(UnlockForm unlockForm);

}
