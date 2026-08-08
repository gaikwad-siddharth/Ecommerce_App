package in.siddharth.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.siddharth.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer>{
	

	public UserEntity findByEmailAndPwd(String email,String pwd);
	
	public UserEntity findByEmail(String email);

	

}
