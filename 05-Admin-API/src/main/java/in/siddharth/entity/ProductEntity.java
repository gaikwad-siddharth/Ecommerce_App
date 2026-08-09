package in.siddharth.entity;


import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class ProductEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer productId;
	private String productName;
	private String productDesc;
	private String productTittle;
	private Integer unitPrice;
	private String imageUrl;
	private Boolean active;
	private Integer unitsStocks;
	private Integer createdBy;
	private Integer updatedBy;
	private LocalDate dateCreated;
	private LocalDate lastUpdated;
	
	@ManyToOne
	@JoinColumn(name = "category_id", referencedColumnName = "categoryId")
	private ProductCategoryEntity category;

}
