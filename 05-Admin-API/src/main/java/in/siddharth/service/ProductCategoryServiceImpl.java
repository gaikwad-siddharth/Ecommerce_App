package in.siddharth.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.siddharth.dto.ProductCategoryDto;
import in.siddharth.entity.ProductCategoryEntity;
import in.siddharth.mapper.ProductCategoryMapper;
import in.siddharth.repo.ProductCategoryRepository;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
	
	@Autowired
	private ProductCategoryRepository productCategoryRepository;

	@Override
	public ProductCategoryDto createProductCategory(ProductCategoryDto productCategoryDto) {
		
		ProductCategoryEntity entity = ProductCategoryMapper.dtoToEntity(productCategoryDto);
		ProductCategoryEntity savedEntity = productCategoryRepository.save(entity); 
		return ProductCategoryMapper.entityToDto(savedEntity);
	}

	@Override
	public List<ProductCategoryDto> getAllProductCategory() {
		
		List<ProductCategoryEntity> entities = productCategoryRepository.findAll();
		return entities.stream().map(ProductCategoryMapper::entityToDto).collect(Collectors.toList());
	}

	@Override
	public ProductCategoryDto getProductCategoryById(Integer categoryId) {
		ProductCategoryEntity entities = productCategoryRepository.findById(categoryId)
				.orElseThrow( () -> new RuntimeException("Product Category Not found eith id :"+ categoryId));
		
		return ProductCategoryMapper.entityToDto(entities);
	}

	@Override
	public ProductCategoryDto updateProductCategory(Integer categoryId, ProductCategoryDto productCategoryDto) {
		ProductCategoryEntity existingEntity = productCategoryRepository.findById(categoryId)
				.orElseThrow( () -> new RuntimeException("Product Category Not found with id :"+ categoryId));
		
		existingEntity.setCategoryName(productCategoryDto.getCategoryName());
		existingEntity.setCategoryDecription(productCategoryDto.getCategoryDecription());
		
		ProductCategoryEntity updatedEntity = productCategoryRepository.save(existingEntity);
		return ProductCategoryMapper.entityToDto(updatedEntity);
	}

	@Override
	public ProductCategoryDto deleteProductCategory(Integer updatedBy, Integer categoryId) {
		ProductCategoryEntity existingEntity = productCategoryRepository.findById(categoryId)
				.orElseThrow( () -> new RuntimeException("Product Category Not found with id :"+ categoryId));
		
		existingEntity.setActiveSw("No");
		existingEntity.setUpdatedBy(updatedBy);
		ProductCategoryEntity updatedEntity = productCategoryRepository.save(existingEntity);
		return ProductCategoryMapper.entityToDto(updatedEntity);
	}

}
