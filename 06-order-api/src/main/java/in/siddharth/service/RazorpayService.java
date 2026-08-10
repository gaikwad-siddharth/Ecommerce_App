package in.siddharth.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.Refund;

@Service
public class RazorpayService {

	@Value("${razorpay.keyId}")
	private String keyId;

	@Value("${razorpay.keySecret}")
	private String keySecret;

	private RazorpayClient razorpayClient;

	public String createRazorpayOrder(Double amount) throws Exception {

		this.razorpayClient = new RazorpayClient(keyId, keySecret);

		JSONObject orderRequest = new JSONObject();

		orderRequest.put("amount", amount * 100); // amount in paise
		orderRequest.put("currency", "INR");
		orderRequest.put("payment_capture", 1);

		Order order = razorpayClient.Orders.create(orderRequest);

		return order.get("id");
	}
	
	public String refundPayment(String paymentId, Double amount) throws Exception {
		
		this.razorpayClient = new RazorpayClient(paymentId, paymentId);
		 
		JSONObject refundRequest = new JSONObject();
		refundRequest.put("payment_id", paymentId);
		refundRequest.put("amount", amount * 100);
		
		Refund refund = razorpayClient.Payments.refund(refundRequest);
		
		return refund.get("id");
	}

}
