package in.siddharth.dto;

import lombok.Data;

@Data
public class PurchaseOrderResponseDto {
	
	private String razorpayOrderId;
	private String orderStatus;
	private String orderTrackingNum;
	private String paymentStatus;

}
