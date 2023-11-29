package in.ies.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.ies.entity.IesRoleEntity;


public interface IesRolesRepo extends JpaRepository<IesRoleEntity,Integer>{

}
