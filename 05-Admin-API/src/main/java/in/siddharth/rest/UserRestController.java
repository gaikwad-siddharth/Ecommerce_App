package in.siddharth.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.siddharth.dto.ApiResponse;
import in.siddharth.dto.UserDto;
import in.siddharth.service.UserService;

@RestController
@RequestMapping("/api/role")
public class UserRestController {

	@Autowired
	private UserService userService;

	@PostMapping("/user")
	public ResponseEntity<ApiResponse<UserDto>> createUser(UserDto userDto) {

		UserDto create = userService.createUser(userDto);
		userDto.setActive(true);
		userDto.setCreatedBy(userDto.getCreatedBy());
		ApiResponse<UserDto> response = new ApiResponse<>();
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMessage("User Created");
		response.setData(create);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@GetMapping("/user")
	public ResponseEntity<ApiResponse<List<UserDto>>> getRoles() {

		List<UserDto> users = userService.getAllUsers();
		ApiResponse<List<UserDto>> response = new ApiResponse<>();

		if (users == null) {
			response.setStatusCode(HttpStatus.NOT_FOUND.value());
			response.setMessage("Users Are Not Available");
			response.setData(null);
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Users Received");
		response.setData(users);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/userbyid/{userId}")
	public ResponseEntity<ApiResponse<UserDto>> getUserById(@PathVariable Integer userId) {

		UserDto users = userService.getUserById(userId);
		ApiResponse<UserDto> response = new ApiResponse<>();

		if (users == null) {
			response.setStatusCode(HttpStatus.NOT_FOUND.value());
			response.setMessage("User Are Not Available");
			response.setData(null);
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("User Received");
		response.setData(users);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/userbyemail/{email}")
	public ResponseEntity<ApiResponse<UserDto>> getUserByEmail(@PathVariable String email) {

		UserDto users = userService.getUserByEmail(email);
		ApiResponse<UserDto> response = new ApiResponse<>();

		if (users == null) {
			response.setStatusCode(HttpStatus.NOT_FOUND.value());
			response.setMessage("User Are Not Available");
			response.setData(null);
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("User Received");
		response.setData(users);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PutMapping("/user/{userId}")
	public ResponseEntity<ApiResponse<UserDto>> updateUser(@PathVariable Integer userId, @RequestBody UserDto userDto) {

		userDto.setUpdatedBy(userDto.getUpdatedBy());
		UserDto users = userService.updateUser(userId, userDto);
		ApiResponse<UserDto> response = new ApiResponse<>();

		if (users == null) {
			response.setStatusCode(HttpStatus.NOT_FOUND.value());
			response.setMessage("Users Are Not Available");
			response.setData(null);
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		response.setStatusCode(HttpStatus.ACCEPTED.value());
		response.setMessage("User Updated");
		response.setData(users);
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/userbyrole/{roleId}")
	public ResponseEntity<ApiResponse<List<UserDto>>> getUserByRole(Integer roleId){
		
		List<UserDto> users = userService.getUsersByRoleId(roleId);
		ApiResponse<List<UserDto>> response = new ApiResponse<>();

		if (users == null) {
			response.setStatusCode(HttpStatus.NOT_FOUND.value());
			response.setMessage("Users Are Not Available");
			response.setData(null);
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Users Received");
		response.setData(users);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@DeleteMapping("/user/{userId}/{updatedBy}")
	public ResponseEntity<ApiResponse<UserDto>> deleteUser(@PathVariable Integer userId, Integer updatedBy) {

		UserDto users = userService.deleteUser(userId, updatedBy);
		ApiResponse<UserDto> response = new ApiResponse<>();

		if (users == null) {
			response.setStatusCode(HttpStatus.NOT_FOUND.value());
			response.setMessage("Users Are Not Available");
			response.setData(null);
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("User Deleted");
		response.setData(users);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

}
