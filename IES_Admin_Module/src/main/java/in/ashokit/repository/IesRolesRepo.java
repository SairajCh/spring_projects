package in.ashokit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.ashokit.entity.IesRolesEntity;


public interface IesRolesRepo extends JpaRepository<IesRolesEntity,Integer>{

}
