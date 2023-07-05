package in.ashokit.runner;

import java.time.LocalDate;
import java.time.Month;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import in.ashokit.entity.IesRolesEntity;
import in.ashokit.entity.IesUserEntity;
import in.ashokit.repository.IesRolesRepo;
import in.ashokit.repository.IesUserRepo;

@Component
public class DataLoader implements ApplicationRunner {
	@Autowired
	public IesRolesRepo iesRolesRepo;

	@Autowired
	public IesUserRepo iesUserRepo;

	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub

		IesRolesEntity role = new IesRolesEntity();

		role.setRole_Id(1);
		role.setRole_name("ADMIN");

		iesRolesRepo.save(role);

		IesRolesEntity user_role = new IesRolesEntity();
		user_role.setRole_Id(2);
		user_role.setRole_name("USER");

		iesRolesRepo.save(user_role);

		IesUserEntity admin = new IesUserEntity();
		admin.setAccountStatus("UNLOCKED");
		admin.setIesRole(role);
		admin.setUserMail("sairajch15@gmail.com");
		admin.setFullName("Sairaj");
		admin.setUserDob(LocalDate.of(1999, Month.MAY, 15));
		admin.setUserPwd("Sairaj@15");

		admin.setCreatedBy(admin);
		admin.setUpdatedBy(admin);
		iesUserRepo.save(admin);

		

	}

}
