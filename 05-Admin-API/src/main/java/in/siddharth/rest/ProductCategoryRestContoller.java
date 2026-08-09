package in.siddharth.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.siddharth.dto.ApiResponse;
import in.siddharth.dto.ProductCategoryDto;
import in.siddharth.service.ProductCategoryService;

@RestController
@RequestMapping("/api/product-category")
public class ProductCategoryRestContoller {
	
	@Autowired
	private ProductCategoryService productCategoryService;
	
	@PostMapping("/product-category")
	public ResponseEntity<ApiResponse<ProductCategoryDto>> createProductCategory(ProductCategoryDto productCategoryDto){
		
		ProductCategoryDto createCategory = productCategoryService.createProductCategory(productCategoryDto);
		productCategoryDto.setActiveSw("Y");
		productCategoryDto.setCreatedBy(productCategoryDto.getCreatedBy());
		ApiResponse<ProductCategoryDto> response = new ApiResponse<>();
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMessage("Category Created");
		response.setData(createCategory);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("/product-categories")
	public ResponseEntity<ApiResponse<List<ProductCategoryDto>>> getAllCategory(){
		
		List<ProductCategoryDto> getAllCategory = productCategoryService.getAllProductCategory();
		ApiResponse<List<ProductCategoryDto>> response = new ApiResponse<>();
		
		if(getAllCategory==null) {
			response.setStatusCode(HttpStatus.NOT_FOUND.value());
			response.setMessage("Product Category Not Available");
			response.setData(null);
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Product Category Received");
		response.setData(getAllCategory);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/product-category/{categoryId}")
	public ResponseEntity<ApiResponse<ProductCategoryDto>> getCategory(@RequestParam Integer categoryId){
		ProductCategoryDto getCategory = productCategoryService.getProductCategoryById(categoryId);
		ApiResponse<ProductCategoryDto> response = new ApiResponse<>();
		
		if(getCategory==null) {
			response.setStatusCode(HttpStatus.NOT_FOUND.value());
			response.setMessage("Product Category Not Found");
			response.setData(null);
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Product Category Received");
		response.setData(getCategory);
		return new ResponseEntity<>(response, HttpStatus.OK);
		
	}
	
	@PutMapping("/product-category/{categoryId}")
	public ResponseEntity<ApiResponse<ProductCategoryDto>> updateCategory(@PathVariable Integer categoryId , @RequestBody ProductCategoryDto productCategoryDto){
		productCategoryDto.setUpdatedBy(productCategoryDto.getUpdatedBy());
		ProductCategoryDto updateCategory = productCategoryService.updateProductCategory(categoryId, productCategoryDto);
		ApiResponse<ProductCategoryDto> response = new ApiResponse<>();
		
		if(updateCategory.getCategoryId()==null) {
			response.setStatusCode(HttpStatus.NOT_FOUND.value());
			response.setMessage("Product Category Not Found");
			response.setData(null);
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		response.setStatusCode(HttpStatus.ACCEPTED.value());
		response.setMessage("Category Updated ");
		response.setData(updateCategory);
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/delete-category/{categoryId}/{updatedBy}")
	public ResponseEntity<ApiResponse<ProductCategoryDto>> deleteCategory(@PathVariable Integer categoryId, Integer updatedBy){
		ProductCategoryDto deleteCategory = productCategoryService.deleteProductCategory( updatedBy, categoryId);
		ApiResponse<ProductCategoryDto> response = new ApiResponse<>();
		
		if(categoryId == null) {
			response.setStatusCode(HttpStatus.NOT_FOUND.value());
			response.setMessage("Product Category Not Found");
			response.setData(null);
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Category Deleted");
		response.setData(deleteCategory);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
