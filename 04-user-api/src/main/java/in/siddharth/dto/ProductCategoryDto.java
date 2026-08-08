package in.siddharth.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ProductCategoryDto {
	
	private Integer categoryId;
	private String categoryName;
	private Integer createdBy;
	private Integer updatedBy;
	private LocalDate cratedDate;
	private LocalDate lastUpdated;


}
