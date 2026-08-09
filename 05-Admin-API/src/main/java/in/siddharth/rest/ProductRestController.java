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
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import in.siddharth.dto.ApiResponse;
import in.siddharth.dto.ProductDto;
import in.siddharth.service.ProductService;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/api/admin")
public class ProductRestController {
	
	@Autowired
	private ProductService productService;
	
	@PostMapping("/product")
	public ResponseEntity<ApiResponse<ProductDto>> createProduct(@RequestParam("categoryId") Integer categoryId,
																@RequestParam("product") String productDtoJson,
																@RequestParam("productImage") MultipartFile productImage) throws Exception{
		

		ObjectMapper objectMapper = new ObjectMapper();
		ProductDto productDto = objectMapper.readValue(productDtoJson, ProductDto.class);
		
		ProductDto createProduct = productService.createProduct(categoryId, productDto, productImage);
		ApiResponse<ProductDto> response = new ApiResponse<>();
		if(createProduct!=null) {
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMessage("Product is Created");
		response.setData(createProduct);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
		}else {
			response.setStatusCode(400);
			response.setMessage("Failed to create product");
			response.setData(null);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/product/{productId}")
	public ResponseEntity<ApiResponse<ProductDto>> getProductById(@PathVariable Integer productId){
		ProductDto products = productService.getProductById(productId);
		ApiResponse<ProductDto> response = new ApiResponse<>();
		
		if(products==null) {
			response.setStatusCode(HttpStatus.NOT_FOUND.value());
			response.setMessage("Product Not Available");
			response.setData(null);
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Product Received");
		response.setData(products);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/product/{categoryId}")
	public ResponseEntity<ApiResponse<List<ProductDto>>> getProductByCategoryId(@PathVariable Integer categoryId){
		List<ProductDto> getProduct =productService.getAllProductByCategoryId(categoryId);
		ApiResponse<List<ProductDto>> response = new ApiResponse<>();
		
		if(getProduct.isEmpty()) {
			response.setStatusCode(HttpStatus.NOT_FOUND.value());
			response.setMessage("Product Not Found");
			response.setData(null);
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Product Received");
		response.setData(getProduct);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/product/name/{name}")
	public ResponseEntity<ApiResponse<List<ProductDto>>> getProductByName(@PathVariable String name){
		List<ProductDto> getProduct =productService.getProductByName(name);
		ApiResponse<List<ProductDto>> response = new ApiResponse<>();
		
		if(getProduct.isEmpty()) {
			response.setStatusCode(HttpStatus.NOT_FOUND.value());
			response.setMessage("Product Not Found");
			response.setData(null);
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Product Received");
		response.setData(getProduct);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PutMapping("/product")
	public ResponseEntity<ApiResponse<ProductDto>> updateProduct( @RequestParam("categoryId") Integer categoryId,
																  @RequestParam("product") String productDtoJson,
																  @RequestParam("productImage") MultipartFile productImage) throws Exception{
		
		ObjectMapper objectMapper = new ObjectMapper();
		ProductDto productDto = objectMapper.readValue(productDtoJson, ProductDto.class);
		
		ProductDto updateProduct = productService.updateProduct(categoryId, productDto, productImage);
		ApiResponse<ProductDto> response = new ApiResponse<>();
		
		if(updateProduct==null) {
			response.setStatusCode(HttpStatus.NOT_FOUND.value());
			response.setMessage("Product Not Found");
			response.setData(null);
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Product Received");
		response.setData(updateProduct);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/product/{productId}/{updatedBy}")
	public ResponseEntity<ApiResponse<ProductDto>> deleteProduct(@PathVariable Integer productId, Integer updatedBy){
		ProductDto deleteProduct = productService.deleteProduct(updatedBy, productId);
		ApiResponse<ProductDto> response = new ApiResponse<>();
		
		if(deleteProduct==null) {
			response.setStatusCode(HttpStatus.NOT_FOUND.value());
			response.setMessage("Product Not Found");
			response.setData(null);
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Product Received");
		response.setData(deleteProduct);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
