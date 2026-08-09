package in.siddharth.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import in.siddharth.dto.ProductDto;
import in.siddharth.entity.ProductEntity;
import in.siddharth.mapper.ProductMapper;
import in.siddharth.repo.ProductCategoryRepository;
import in.siddharth.repo.ProductRepository;

import java.nio.file.Paths;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductCategoryRepository productCategoryRepository;
	
	@Value("${app.images.upload-dir}")
	private String imagesUploadDir;

	@Override
	public ProductDto createProduct(Integer categoryId, ProductDto productDto, MultipartFile productImage) {
		
		String originalFilename = productImage.getOriginalFilename();
		 Path filePath = Paths.get(imagesUploadDir + originalFilename);
		 
		 if(!Files.exists(filePath)){
			 try {
				 Files.createDirectories(filePath.getParent());
				 Files.copy(productImage.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
			 }catch(IOException e){
				 throw new RuntimeException("Failed to save poduct image", e);
			 }
		 }
		 
		 productDto.setImageUrl(filePath.toString());
		
		 ProductEntity entity = ProductMapper.dtoToEntity(productDto);

		entity.setImageUrl(filePath.toString());
		
		productCategoryRepository.findById(categoryId).ifPresent(category -> {
			entity.setCategory(category);
		});

		ProductEntity savedEntity = productRepository.save(entity);

		return ProductMapper.entityToDto(savedEntity);
	}

	@Override
	public List<ProductDto> getAllProductByCategoryId(Integer categoryId) {

	    List<ProductEntity> entities =
	            productRepository.findByCategoryCategoryId(categoryId);

	    return entities.stream()
	            .map(ProductMapper::entityToDto)
	            .toList();
	}

	@Override
	public ProductDto getProductById(Integer productId) {
		
		ProductEntity entities = productRepository.findById(productId)
		.orElseThrow( () -> new RuntimeException("Product Not Found" + productId));
		return ProductMapper.entityToDto(entities);
	}

	@Override
	public ProductDto updateProduct(Integer productId,
	                                ProductDto productDto,
	                                MultipartFile productImage) {

	    ProductEntity existingEntity = productRepository.findById(productId)
	            .orElseThrow(() ->
	                    new RuntimeException("Product Not Found : " + productId));

	    if (productImage != null && !productImage.isEmpty()) {

	        String originalFilename = productImage.getOriginalFilename();

	        Path filePath = Paths.get(imagesUploadDir, originalFilename);

	        try {
	            Files.createDirectories(filePath.getParent());

	            Files.copy(productImage.getInputStream(),
	                    filePath,
	                    StandardCopyOption.REPLACE_EXISTING);

	            existingEntity.setImageUrl(filePath.toString());

	        } catch (IOException e) {
	            throw new RuntimeException("Failed to save product image", e);
	        }
	    }

	    existingEntity.setProductName(productDto.getProductName());
	    existingEntity.setProductDesc(productDto.getProductDesc());
	    existingEntity.setUnitPrice(productDto.getUnitPrice());
	    existingEntity.setUnitsStocks(productDto.getUnitsStocks());
	    existingEntity.setActive(productDto.getActive());

	    ProductEntity updatedEntity = productRepository.save(existingEntity);

	    return ProductMapper.entityToDto(updatedEntity);
	}
	
	@Override
	public List<ProductDto> getProductByName(String productName) {

	    List<ProductEntity> entities = productRepository.findByProductName(productName);

	    return entities.stream()
	            .map(ProductMapper::entityToDto)
	            .toList();
	}

	@Override
	public ProductDto deleteProduct(Integer updatedBy, Integer productId) {
		ProductEntity entity =productRepository.findById(productId)
		.orElseThrow( () -> new RuntimeException("Product Not Found" + productId));
		
		entity.setActive(false);
		entity.setUpdatedBy(updatedBy);
		ProductEntity updatedEntity = productRepository.save(entity);
		return ProductMapper.entityToDto(updatedEntity);
	}

}
