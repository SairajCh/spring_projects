package in.ies.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ies.entity.IesPlanEntity;

public interface IesPlansRepo extends JpaRepository<IesPlanEntity,Integer> {
	
	

}
