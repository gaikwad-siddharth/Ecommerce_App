package in.siddharth.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.siddharth.entity.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Integer>{
	
	public RoleEntity findByRoleName(String roleName);

}
