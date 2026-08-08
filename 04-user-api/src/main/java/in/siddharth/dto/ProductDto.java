package in.siddharth.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import in.siddharth.entity.ProductCategoryEntity;
import lombok.Data;

@Data
public class ProductDto {
	
	private Integer productId;
	private String productName;
	private String productDesc;
	private String productTittle;
	private BigDecimal unitPrice;
	private String imagUrl;
	private Boolean active;
	private Integer unitsStocks;
	private Integer createdBy;
	private Integer updatedBy;
	private LocalDate dateCreated;
	private LocalDate lastUpdated;
	private ProductCategoryEntity category;

}
