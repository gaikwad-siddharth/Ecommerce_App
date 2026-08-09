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
import in.siddharth.dto.RoleDto;
import in.siddharth.service.RoleService;

@RestController
@RequestMapping("/api/role")
public class RoleRestController {
	
	@Autowired
	private RoleService roleService;
	
	@PostMapping("/role")
	public ResponseEntity<ApiResponse<RoleDto>> createRole(RoleDto roleDto){
		RoleDto createRole = roleService.createRole(roleDto);
		roleDto.setActive(true);
		roleDto.setCreatedBy(roleDto.getCreatedBy());
		ApiResponse<RoleDto> response = new ApiResponse<>();
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMessage("Role Created");
		response.setData(createRole);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("/roles")
	public ResponseEntity<ApiResponse<List<RoleDto>>> getRoles(){
		
		List<RoleDto> getroles = roleService.getAllRoles();
		ApiResponse<List<RoleDto>> response = new ApiResponse<>();
		if(getroles==null) {
			response.setStatusCode(HttpStatus.NOT_FOUND.value());
			response.setMessage("Roles Are Not Available");
			response.setData(null);
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Roles Received");
		response.setData(getroles);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/rolebyid/{roleId}")
	public ResponseEntity<ApiResponse<RoleDto>> getRoleById(@PathVariable Integer roleId){
		
		RoleDto getRole = roleService.getRoleById(roleId);
		ApiResponse<RoleDto> response = new ApiResponse<>();
		
		if(getRole == null) {
			response.setStatusCode(HttpStatus.NOT_FOUND.value());
			response.setMessage("Roles Are Not Available");
			response.setData(null);
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Role Received");
		response.setData(getRole);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/rolebyname/{roleName}")
	public ResponseEntity<ApiResponse<RoleDto>> getRoleByName(@PathVariable String roleName){
		
		RoleDto getRole = roleService.getRoleByName(roleName);
		ApiResponse<RoleDto> response = new ApiResponse<>();
		
		if(getRole == null) {
			response.setStatusCode(HttpStatus.NOT_FOUND.value());
			response.setMessage("Roles Are Not Available");
			response.setData(null);
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Role Received");
		response.setData(getRole);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PutMapping("/role/{roleId}")
	public ResponseEntity<ApiResponse<RoleDto>> updateRole(@PathVariable Integer roleId, @RequestBody RoleDto roleDto){
		roleDto.setUpdatedBy(roleDto.getUpdatedBy());
		RoleDto updateRole = roleService.updateRole(roleId, roleDto);
		ApiResponse<RoleDto> response = new ApiResponse<>();
		
		if(updateRole.getRoleId() == null) {
			response.setStatusCode(HttpStatus.NOT_FOUND.value());
			response.setMessage("Roles Are Not Available");
			response.setData(null);
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		response.setStatusCode(HttpStatus.ACCEPTED.value());
		response.setMessage("Role Updated ");
		response.setData(updateRole);
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/role/{roleId}/{updatedBy}")
	public ResponseEntity<ApiResponse<RoleDto>> deleteRole(@PathVariable Integer roleId, Integer updatedBy){
		RoleDto deleteRole = roleService.deleteRole(roleId, updatedBy);
		ApiResponse<RoleDto> response = new ApiResponse<>();
		
		if(deleteRole.getRoleId() == null) {
			response.setStatusCode(HttpStatus.NOT_FOUND.value());
			response.setMessage("Roles Are Not Available");
			response.setData(null);
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Role Received");
		response.setData(deleteRole);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	

}
