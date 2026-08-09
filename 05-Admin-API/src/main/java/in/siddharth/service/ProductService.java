package in.siddharth.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import in.siddharth.dto.ProductDto;

public interface ProductService {
	
	public ProductDto createProduct(Integer categoryId, ProductDto productDto, MultipartFile productImage);
	
	public ProductDto updateProduct(Integer productId, ProductDto productDto, MultipartFile productImage);
	
	public List<ProductDto> getAllProductByCategoryId(Integer categoryId);
	
	public ProductDto getProductById(Integer productId);	
	
	public List<ProductDto> getProductByName(String productName);
	
	public ProductDto deleteProduct(Integer updatedBy, Integer productId);

}
