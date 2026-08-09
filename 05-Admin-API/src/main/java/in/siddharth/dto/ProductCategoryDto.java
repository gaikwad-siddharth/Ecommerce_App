package in.siddharth.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ProductCategoryDto {
	
	private Integer categoryId;
	private String categoryName;
	private String categoryDecription;
	private String activeSw;
	private Integer createdBy;
	private Integer updatedBy;
	private LocalDate createdDate;
	private LocalDate lastUpdated;


}
