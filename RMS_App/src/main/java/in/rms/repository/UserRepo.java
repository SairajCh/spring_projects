package in.rms.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import in.rms.entity.User;


public interface UserRepo extends JpaRepository<User,Integer> {
	
	User findByEmail(String email);
	
	@Query("from User u where u.role='user'")
	List<User> getAllUsers();
	
	@Transactional
	@Modifying
	@Query("update User u set u.status=:status where u.id=:id")
	Integer updateStatus(String status,Integer id);
	
	@Query("select u.email from User u where u.role='admin'")

	List<String> getAllAdmin();
	
	
	
	
	
	
	
	
	

}
