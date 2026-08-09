package in.siddharth.dto;

import lombok.Data;

@Data
public class ShippingAddressDto {
	
	private Integer addressId;
	private Integer houseNum;
	private String country;
	private String state;
	private String city;
	private Integer zipcode;

}
