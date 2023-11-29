package in.ies.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.ies.entities.AppEntity;

public interface AppRepo extends JpaRepository<AppEntity,Long>{
	
	
	@Query(value="from AppEntity")
	public List<AppEntity> fetchUserApps();
	
	@Query(value="from AppEntity where userId=:userId")
	public List<AppEntity> fetchCwApps(Integer userId);
	
	

}
