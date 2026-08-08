package in.siddharth.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class ProductCategoryEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer categoryId;
	private String categoryName;
	private String categoryDecription;
	private String activeSw;
	private Integer createdBy;
	private Integer updatedBy;
	
	@CreationTimestamp
	private LocalDate cratedDate;
	
	@UpdateTimestamp
	private LocalDate lastUpdated;

}
