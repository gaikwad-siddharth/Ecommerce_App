package in.siddharth.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.siddharth.entity.OrderEntity;

public interface OrderItemRepository extends JpaRepository<OrderEntity, Integer>{

}
