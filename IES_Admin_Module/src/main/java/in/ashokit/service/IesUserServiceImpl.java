package in.ashokit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.binding.LoginForm;
import in.ashokit.binding.UnlockForm;
import in.ashokit.entity.IesUserEntity;
import in.ashokit.repository.IesUserRepo;

@Service
public class IesUserServiceImpl implements IesUserService{
	
	@Autowired
	private IesUserRepo iesUserRepo;
	
	
	
	public String login(LoginForm loginForm) {
		// TODO Auto-generated method stub
		 IesUserEntity iesUserEntity = iesUserRepo.findByUserMailAndUserPwd(loginForm.getUserMail(), loginForm.getUserPwd());
		
		 if(iesUserEntity == null) {
			 return "Invalid Credentials";
		 }else if(iesUserEntity.getAccountStatus().equals("LOCKED")) {
			 return "Unlock your account";
		 }else {
			 return "successs";
		 }
		
	}

	public String forgotPwd(String email) {
		// TODO Auto-generated method stub
		
		
		
		
		return null;
	}

	

	public boolean unlockAccount(UnlockForm unlockForm) {
		// TODO Auto-generated method stub
		
		IesUserEntity iesUserEntity = iesUserRepo.findByUserMail(unlockForm.getUserEmail());
		
		if(unlockForm.getUserPwd()==iesUserEntity.getUserPwd()) {
			iesUserEntity.setAccountStatus("UNLOCKED");
			iesUserEntity.setUserPwd(unlockForm.getNewPwd());
			iesUserRepo.save(iesUserEntity);
			return true;
		}
		return false;
	}
	
}
