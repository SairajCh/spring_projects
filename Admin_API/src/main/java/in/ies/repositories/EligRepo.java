package in.ies.repositories;

import javax.persistence.criteria.CriteriaBuilder.In;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ies.entities.EligEntity;

public interface EligRepo extends JpaRepository<EligEntity, Integer>{

}
