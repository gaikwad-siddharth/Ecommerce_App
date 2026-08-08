package in.siddharth.service;

import in.siddharth.dto.ResetPwdDto;
import in.siddharth.dto.ShippingAddressDto;
import in.siddharth.dto.UserDto;

public interface UserService {
	
	public UserDto getUserByEmail(String email);
	
	public UserDto savedUser(UserDto userDto);
	
	public ResetPwdDto resetPwd(ResetPwdDto resetPwdDto);
	
	public UserDto login (String email, String pwd);
	
	public ShippingAddressDto saveShippingAddress (Integer userId, ShippingAddressDto shippingAddressDto);
	
	public ShippingAddressDto deleteShippingAddress (Integer shippingAddressId);
}
