package in.ashokit.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import in.ashokit.entity.IesUserEntity;
import java.lang.String;
import java.util.List;


public interface IesUserRepo extends JpaRepository<IesUserEntity,Integer>{
	
public IesUserEntity findByUserMailAndUserPwd(String userMail, String userPwd);
   
public IesUserEntity findByUserMail(String userMail);

}
