package in.siddharth.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.siddharth.entity.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer>{
	
	public List<OrderEntity> findByUserEmail(String email);
}
