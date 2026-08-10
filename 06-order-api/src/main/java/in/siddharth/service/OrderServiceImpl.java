package in.siddharth.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import in.siddharth.dto.OrderDto;
import in.siddharth.dto.OrderItemDto;
import in.siddharth.dto.PurchaseOrderRequestDto;
import in.siddharth.dto.PurchaseOrderResponseDto;
import in.siddharth.dto.ShippingAddressDto;
import in.siddharth.dto.UserDto;
import in.siddharth.entity.OrderEntity;
import in.siddharth.entity.OrderItemEntity;
import in.siddharth.entity.ShippingAddressEntity;
import in.siddharth.entity.UserEntity;
import in.siddharth.mapper.OrderMapper;
import in.siddharth.repo.OrderItemRepository;
import in.siddharth.repo.OrderRepository;
import in.siddharth.repo.ShippingAddressRepository;
import in.siddharth.repo.UserRepository;

public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ShippingAddressRepository shippingAddressRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private RazorpayService razorpayService;

	@Override
	public PurchaseOrderResponseDto createOrder(PurchaseOrderRequestDto purchaseOrderRequestDto) {
		
		UserDto user = purchaseOrderRequestDto.getUser();
		ShippingAddressDto shippingAddress = purchaseOrderRequestDto.getShippingAddress();
		OrderDto order = purchaseOrderRequestDto.getOrder();
		List<OrderItemDto> orderItems = purchaseOrderRequestDto.getOrderItems();
		
		String orderTrackingNumber = generateOrderTrackingNumber();
		
		UserEntity userEntity = userRepository.findByEmail(user.getEmail());
		if(userEntity == null) {
			userEntity = new UserEntity();
			userEntity.setEmail(user.getEmail());
			userEntity.setName(user.getName());
			userEntity.setPwd(user.getPwd());
			userEntity = userRepository.save(userEntity);
		}
		ShippingAddressEntity addressEntity = null;
		if(shippingAddress.getAddressId() == null) {
			addressEntity.setHouseNum(shippingAddress.getHouseNum());
			addressEntity.setCity(shippingAddress.getCity());
			addressEntity.setState(addressEntity.getState());
			addressEntity.setZipcode(shippingAddress.getZipcode());
			addressEntity = shippingAddressRepository.save(addressEntity);
		}else {
			addressEntity = shippingAddressRepository.findById(shippingAddress.getAddressId()).orElse(null);
		}
		
		OrderEntity orderEntity = new OrderEntity();
		orderEntity.setOrderTrackingNum(orderTrackingNumber);
		orderEntity.setPrice(order.getPrice());
		orderEntity.setQuantity(order.getQuantity());
		//orderEntity.setEmail(order.getEmail());
		
		orderEntity.setStatus("CREATED");
		orderEntity.setPaymentStatus("PENDING");
		
		orderEntity.setUser(userEntity);
		orderEntity.setAddr(addressEntity);
		
		orderEntity = orderRepository.save(orderEntity);
		
		for(OrderItemDto itemDto : orderItems) {
			
			OrderItemEntity itemEntity = new OrderItemEntity();
			itemEntity.setProductId(itemDto.getProductId());
			itemEntity.setProductName(itemDto.getProductName());
			itemEntity.setUnitPrice(itemDto.getUnitPrice());
			itemEntity.setQuantity(itemDto.getQuantity());
			itemEntity.setOrder(orderEntity);
			orderItemRepository.save(itemEntity);
			
		}
		
		PurchaseOrderResponseDto responseDto = new PurchaseOrderResponseDto();
		responseDto.setOrderTrackingNum(orderTrackingNumber);
		responseDto.setRazorpayOrderId(razorpayOrderId);
		responseDto.setOrderStatus(orderEntity.getStatus());
		responseDto.setPaymentStatus(orderEntity.getPaymentStatus());
		
		return responseDto;
	}

	@Override
	public OrderDto updateOrder(Integer orderId, OrderDto orderDto) {
		
		OrderEntity orderEntity = orderRepository.findById(orderId).orElseThrow( () -> new RuntimeException("Order Not Found"+orderId));
		
		if(orderEntity!=null) {
			orderEntity.setRezorpayOrderId(orderDto.getRezorpayOrderId());
			orderEntity.setStatus(orderDto.getOrderStatus());
			orderEntity.setPaymentStatus(orderDto.getPaymentStatus());
			orderEntity = orderRepository.save(orderEntity);
		}
		return OrderMapper.entityToDto(orderEntity);
	}

	@Override
	public OrderDto cancelOrder(String orderTrackingNumber) {
		OrderEntity orderEntity = orderRepository.findById(orderId)
		        .orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));

		    if (orderEntity != null) {
		        razorpayService.refundPayment(
		            orderEntity.getRazorpayOrderId(),
		            orderEntity.getTotalPrice()
		        );

		        orderEntity.setRazorpayOrderId(orderDto.getRazorpayOrderId()); // <-- problem
		        orderEntity.setOrderStatus("CANCELLED");
		        orderEntity.setPaymentStatus("REFUND-IN-PROGRESS");

		        orderEntity = orderRepository.save(orderEntity);
		    }

		    return OrderMapper.toDto(orderEntity);
	}

	@Override
	public List<OrderDto> getAllOrders(String email) {
		return orderRepository.findByUserEmail(email).stream().map(OrderMapper :: entityToDto).collect(Collectors.toList());
	}
	
	private String generateOrderTrackingNumber() {
		return java.util.UUID.randomUUID().toString();
	}

}
