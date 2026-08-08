package in.siddharth.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.siddharth.dto.ApiResponse;
import in.siddharth.dto.ResetPwdDto;
import in.siddharth.dto.ShippingAddressDto;
import in.siddharth.dto.UserDto;
import in.siddharth.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserRestController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/")
	public ResponseEntity<ApiResponse<UserDto>> createUser(@RequestBody UserDto userDto){
		UserDto savedUser= userService.savedUser(userDto);
		ApiResponse<UserDto> response = new ApiResponse<>();
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMessage("User Created Successfully");
		response.setData(savedUser);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<ApiResponse<UserDto>> loginUser(UserDto userDto){
		ApiResponse<UserDto> response = new ApiResponse<>();
		UserDto loggedInUser = userService.login(userDto.getEmail(), userDto.getPwd());
		
		if(loggedInUser == null) {
			response.setStatusCode(HttpStatus.UNAUTHORIZED.value());
			response.setMessage("Invalid email or password");
			response.setData(null);
			return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);

		}
	
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Login Successfully");
		response.setData(loggedInUser);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping("/reset-password")
	public ResponseEntity<ApiResponse<UserDto>> resetPassword(@RequestBody ResetPwdDto resetPwdDto){
		
		ResetPwdDto resetPwdDto1 = userService.resetPwd(resetPwdDto);
		ApiResponse<UserDto> response = new ApiResponse<>();
		
		if(resetPwdDto1==null) {
			response.setStatusCode(HttpStatus.BAD_REQUEST.value());
			response.setMessage("Invalid email or password");
			response.setData(null);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMessage("Password Reset Successfully");
		response.setData(null);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
		
	}
	
	@GetMapping("/email")
	public ResponseEntity<ApiResponse<UserDto>> getUserByEmail(@RequestParam String email){
		UserDto userDto = userService.getUserByEmail(email);
		ApiResponse<UserDto> response = new ApiResponse<>();
		
		if(userDto==null) {
			response.setStatusCode(HttpStatus.NOT_FOUND.value());
			response.setMessage("User not found with email: "+email);
			response.setData(null);
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("User Received Successfully");
		response.setData(userDto);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping("/{userId}/shipping-address")
	public ResponseEntity<ApiResponse<ShippingAddressDto>> saveShippingAddress(@PathVariable Integer userId,@RequestBody  ShippingAddressDto shippingAddressDto){
		ShippingAddressDto saveAddress = userService.saveShippingAddress(userId, shippingAddressDto);
		ApiResponse<ShippingAddressDto> response = new ApiResponse<>();
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMessage("Shipping Address Saved Successfully");
		response.setData(saveAddress);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/shipping-address/{shippingAddressId}")
	public ResponseEntity<ApiResponse<ShippingAddressDto>> deleteShippingAddress(@PathVariable Integer shippingAddressId){
		ShippingAddressDto deleteUser = userService.deleteShippingAddress(shippingAddressId);
		ApiResponse<ShippingAddressDto> response = new ApiResponse<>();
		
		if(shippingAddressId==null) {
			response.setStatusCode(HttpStatus.NOT_FOUND.value());
			response.setMessage("User not found with id: "+shippingAddressId);
			response.setData(null);
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Shipping Address deleted Successfully");
		response.setData(deleteUser);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
