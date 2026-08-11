package in.siddharth.entity;

import java.math.BigDecimal;

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
public class OrderItemEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orderItemId;
	private String imageUrl;
	private BigDecimal unitPrice;
	
	@ManyToOne
	@JoinColumn(name = "product_id", referencedColumnName = "productId")
	private ProductEntity product;
	
	@ManyToOne
	@JoinColumn(name="order_id", referencedColumnName = "orderId")
	private OrderEntity order;

}
