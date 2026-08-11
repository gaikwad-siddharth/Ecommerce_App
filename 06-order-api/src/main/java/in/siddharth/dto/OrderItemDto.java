package in.siddharth.dto;

import java.math.BigDecimal;

import in.siddharth.entity.OrderEntity;
import in.siddharth.entity.ProductEntity;
import lombok.Data;

@Data
public class OrderItemDto {
	
	private Integer orderItemId;
	private String imageUrl;
	private BigDecimal unitPrice;
	private ProductEntity product;
	private OrderEntity order;

}
