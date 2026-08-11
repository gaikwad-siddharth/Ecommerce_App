package in.siddharth.dto;

import java.util.List;

import lombok.Data;

@Data
public class PurchaseOrderRequestDto {
	
	private UserDto user;
	
	private ShippingAddressDto shippingAddress;
	
	private OrderDto order;
	
	private List<OrderItemDto> orderItems;

}
