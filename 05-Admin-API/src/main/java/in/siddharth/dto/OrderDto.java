package in.siddharth.dto;

import java.time.LocalDate;

import in.siddharth.entity.ShippingAddressEntity;
import in.siddharth.entity.UserEntity;
import lombok.Data;

@Data
public class OrderDto {
	
	private Integer orderId;
	private Long orderTrackingNum;
	private Integer quantity;
	private Integer price;
	private String orderStatus;
	private Integer rezorpayOrderId;
	private Integer rezorpayPaymentId;
	private UserEntity user;
	private ShippingAddressEntity addr;
	private LocalDate createdDate;
	private LocalDate updatedDate;

}
