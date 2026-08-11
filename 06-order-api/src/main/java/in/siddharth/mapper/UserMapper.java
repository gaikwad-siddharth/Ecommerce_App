package in.siddharth.mapper;

import org.modelmapper.ModelMapper;

import in.siddharth.dto.UserDto;
import in.siddharth.entity.UserEntity;

public class UserMapper {
	
	public static final ModelMapper mapper = new ModelMapper();

	public static UserDto entityToDto(UserEntity userEntity) {
		return mapper.map(userEntity, UserDto.class);
	}
	
	public static UserEntity dtoToEntity(UserDto userDto) {
		return mapper.map(userDto, UserEntity.class);
	}
}
