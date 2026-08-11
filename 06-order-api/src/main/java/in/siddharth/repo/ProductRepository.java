package in.siddharth.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.siddharth.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer>{
	
	public List<ProductEntity> findByProductName(String productName);
	
	public List<ProductEntity> findByCategoryCategoryId(Integer categoryId);
}
