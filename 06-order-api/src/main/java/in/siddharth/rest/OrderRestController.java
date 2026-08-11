package in.siddharth.rest;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.siddharth.dto.ApiResponse;
import in.siddharth.dto.OrderDto;
import in.siddharth.dto.PurchaseOrderRequestDto;
import in.siddharth.dto.PurchaseOrderResponseDto;
import in.siddharth.service.OrderService;

@RestController
@RequestMapping("/api/order")
public class OrderRestController {

	@Autowired
	private OrderService orderService;
	
	@PostMapping("/create")
	public ResponseEntity<ApiResponse<PurchaseOrderResponseDto>> createOrder(@RequestBody PurchaseOrderRequestDto orderReqDto){
		PurchaseOrderResponseDto order =  orderService.createOrder(orderReqDto);
		ApiResponse<PurchaseOrderResponseDto> response = new ApiResponse<>();
		
		if(order != null) {
			response.setStatusCode(201);
			response.setMessage("Order Placed Successfully");
			response.setData(order);
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		}else {
			response.setStatusCode(500);
			response.setMessage("Failed to create Order");
			response.setData(null);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/update/{orderId}")
	public ResponseEntity<ApiResponse<OrderDto>> updateOrder(@PathVariable Integer orderId, @RequestBody OrderDto orderDto){
		OrderDto updateOrder = orderService.updateOrder(orderId, orderDto);
		ApiResponse<OrderDto> response = new ApiResponse<>();
		
		if(updateOrder != null) {
			response.setStatusCode(200);
			response.setMessage("Order Updated Successfully");
			response.setData(updateOrder);
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		}else {
			response.setStatusCode(500);
			response.setMessage("Failed to update Order");
			response.setData(null);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/cancel/{orderId}")
	public ResponseEntity<ApiResponse<OrderDto>> cancelOrder(@PathVariable Integer orderId){
		OrderDto cancelOrder = orderService.cancelOrder(orderId);
		ApiResponse<OrderDto> response = new ApiResponse<>();
		
		if(cancelOrder != null) {
			response.setStatusCode(200);
			response.setMessage("Order Updated Successfully");
			response.setData(cancelOrder);
			return new ResponseEntity<>(response, HttpStatus.OK);
		}else {
			response.setStatusCode(500);
			response.setMessage("Failed to update Order");
			response.setData(null);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/customer/{email}")
	public ResponseEntity<ApiResponse<List<OrderDto>>> getCustomersOrder(String email){
		
		ApiResponse<List<OrderDto>> response = new ApiResponse<>();
		List<OrderDto> allOrders = orderService.getAllOrders(email);
		
		if(!allOrders.isEmpty()) {
			response.setStatusCode(200);
			response.setMessage("Order Fetched Successfully");
			response.setData(allOrders);
			return new ResponseEntity<>(response, HttpStatus.OK);
		}else {
			response.setStatusCode(200);
			response.setMessage("No Orders Found ");
			response.setData(Collections.emptyList());
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		
	}

}
