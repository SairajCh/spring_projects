package in.ashokit.service;

import in.ashokit.binding.CaseworkerForm;

public interface IesAdminService {
	
	public boolean createCaseworker(CaseworkerForm caseworkerForm, Integer Id);
	
	
	public boolean createPlan();
	
	public boolean deactivateUser();
	
	public boolean deactivatePlan();

	
}
