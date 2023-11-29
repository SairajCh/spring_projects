package in.ies.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ies.entity.IesUserEntity;

import java.lang.String;
import java.util.List;


public interface IesUserRepo extends JpaRepository<IesUserEntity,Integer>{
	
public IesUserEntity findByUserMailAndUserPwd(String userMail, String userPwd);
   
public IesUserEntity findByUserMail(String userMail);

}
