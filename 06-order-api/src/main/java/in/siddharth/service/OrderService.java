package in.siddharth.service;

import java.util.List;

import in.siddharth.dto.OrderDto;
import in.siddharth.dto.PurchaseOrderRequestDto;
import in.siddharth.dto.PurchaseOrderResponseDto;

public interface OrderService {
	
	public PurchaseOrderResponseDto createOrder(PurchaseOrderRequestDto purchaseOrderRequestDto);
	
	public OrderDto updateOrder(Integer orderId, OrderDto orderDto);
	
	public OrderDto cancelOrder(Integer orderId);
	
	public List<OrderDto> getAllOrders(String email);

}
