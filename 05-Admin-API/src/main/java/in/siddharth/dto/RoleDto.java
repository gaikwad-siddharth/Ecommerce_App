package in.siddharth.dto;

import lombok.Data;

@Data
public class RoleDto {
	
	private Integer roleId;
	private String roleName;
	private boolean active;
	private Integer createdBy;
	private Integer updatedBy;

}
