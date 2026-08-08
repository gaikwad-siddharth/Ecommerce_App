package in.siddharth.mapper;

import org.modelmapper.ModelMapper;

import in.siddharth.dto.UserDto;
import in.siddharth.entity.UserEntity;

public class UserMapper {
	
	public static final ModelMapper mapper = new ModelMapper();
	
	public static UserDto entityToDto(UserEntity entity) {
		return mapper.map(entity, UserDto.class);
	}
	
	public static UserEntity dtoToEntity(UserDto dto) {
		return mapper.map(dto, UserEntity.class);
	}
}
