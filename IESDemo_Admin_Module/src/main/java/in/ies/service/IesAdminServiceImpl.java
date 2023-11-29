package in.ies.service;

import org.springframework.beans.factory.annotation.Autowired;

import in.ies.binding.CaseworkerForm;
import in.ies.entity.IesUserEntity;
import in.ies.repository.IesPlansRepo;
import in.ies.repository.IesUserRepo;

public class IesAdminServiceImpl implements IesAdminService {

	@Autowired
	private IesPlansRepo iesPlansRepo;

	@Autowired
	private IesUserRepo iesUserRepo;

	public boolean createCaseworker(CaseworkerForm caseworkerForm, Integer Id) {

		IesUserEntity entity = new IesUserEntity();

		// TODO Auto-generated method stub
		return false;
	}

	public boolean createPlan() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean deactivateUser() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean deactivatePlan() {
		// TODO Auto-generated method stub
		return false;
	}

}
