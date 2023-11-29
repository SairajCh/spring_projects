package in.ies.service;

import in.ies.bindings.DashboardCard;
import in.ies.bindings.LoginForm;
import in.ies.bindings.UserAccountForm;

public interface UserService {
	
	public String login(LoginForm loginForm);
	
	public boolean recoverPassword(String email);
	
	public DashboardCard fetchDashboardInfo();
	
	public UserAccountForm getUserByEmail(String email);
	

	

}
