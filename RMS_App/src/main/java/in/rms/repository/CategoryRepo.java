package in.rms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.rms.entity.Category;

public interface CategoryRepo extends JpaRepository<Category,Integer>{

	List<Category> getAllCategory();
	
	
	

}
