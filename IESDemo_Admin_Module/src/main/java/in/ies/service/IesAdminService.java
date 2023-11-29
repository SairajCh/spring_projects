package in.ies.service;

import in.ies.binding.CaseworkerForm;

public interface IesAdminService {
	
	public boolean createCaseworker(CaseworkerForm caseworkerForm, Integer Id);
	
	
	public boolean createPlan();
	
	public boolean deactivateUser();
	
	public boolean deactivatePlan();

	
}
