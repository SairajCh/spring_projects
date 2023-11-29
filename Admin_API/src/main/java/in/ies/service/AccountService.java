package in.ies.service;

import java.util.List;

import in.ies.bindings.UnlockAccForm;
import in.ies.bindings.UserAccountForm;

public interface AccountService {
	
	public Boolean createUserAccount(UserAccountForm accForm);
	
	public List<UserAccountForm> fetchUserAccounts();
	
	public UserAccountForm getUserAccById(Integer accId);
	
	public String changeAccStatus(Integer accId, String status);
	
	public String unlockUserAccount(UnlockAccForm unlockAccForm);
	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
	 
	

}
