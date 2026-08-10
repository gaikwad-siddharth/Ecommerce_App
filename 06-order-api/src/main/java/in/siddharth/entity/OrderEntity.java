package in.siddharth.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

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
public class OrderEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orderId;
	private String orderTrackingNum;
	private Integer quantity;
	private Integer price;
	private String Status;
	private String PaymentStatus;
	private Integer rezorpayOrderId;
	private Integer rezorpayPaymentId;
	private LocalDateTime deliveryDate;
	
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "userId")
	private UserEntity user;
	
	@ManyToOne
	@JoinColumn(name = "addr_id", referencedColumnName = "addrId")
	private ShippingAddressEntity addr;
	
	@CreatedDate
	private LocalDate createdDate;
	
	@UpdateTimestamp
	private LocalDate updatedDate;

}
