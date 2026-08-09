package in.siddharth.service;

import java.util.List;

import in.siddharth.dto.ProductCategoryDto;

public interface ProductCategoryService {
	
	public ProductCategoryDto createProductCategory(ProductCategoryDto productCategoryDto);
	
	public List<ProductCategoryDto> getAllProductCategory();
	
	public ProductCategoryDto getProductCategoryById(Integer categoryId);
	
	public ProductCategoryDto updateProductCategory(Integer categoryId, ProductCategoryDto productCategoryDto);
	
	public ProductCategoryDto deleteProductCategory(Integer updatedBy, Integer categoryId);

}
