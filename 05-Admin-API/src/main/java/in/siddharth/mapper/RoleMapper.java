package in.siddharth.mapper;

import org.modelmapper.ModelMapper;

import in.siddharth.dto.RoleDto;
import in.siddharth.entity.RoleEntity;


public class RoleMapper {
	public static final ModelMapper mapper = new ModelMapper();

	public static RoleDto entityToDto(RoleEntity roleEntity) {
		return mapper.map(roleEntity, RoleDto.class);
	}
	
	public static RoleEntity dtoToEntity(RoleDto roleDto) {
		return mapper.map(roleDto, RoleEntity.class);
	}

}
