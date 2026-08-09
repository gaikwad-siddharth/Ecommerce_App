package in.siddharth.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.siddharth.entity.ShippingAddressEntity;

public interface ShippingAddressRepository extends JpaRepository<ShippingAddressEntity, Integer>{

}
