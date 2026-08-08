package in.siddharth.dto;

import java.time.LocalDate;


import lombok.Data;

@Data
public class UserDto {
	
	private Integer userId;
	private String name;
	private String email;
	private String pwd;
	private Long phno;
	private String pwdUpdated; 
	private LocalDate createdDate;
	private LocalDate updateDate;
	public String roleName;
	

}
