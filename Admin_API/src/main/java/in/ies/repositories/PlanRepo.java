package in.ies.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ies.entities.PlanEntity;

public interface PlanRepo extends JpaRepository<PlanEntity,Integer> {

	
	
}
