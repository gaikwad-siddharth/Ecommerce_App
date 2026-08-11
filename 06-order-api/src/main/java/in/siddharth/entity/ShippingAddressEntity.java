package in.siddharth.entity;

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
public class ShippingAddressEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer addrId;
	private Integer houseNum;
	private String country;
	private String state;
	private String city;
	private Integer zipcode;
	
	@ManyToOne
	@JoinColumn(name="user_id", referencedColumnName = "userId")
	private UserEntity user;

}
