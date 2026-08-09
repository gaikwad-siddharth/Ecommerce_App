package in.siddharth.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.siddharth.dto.RoleDto;
import in.siddharth.entity.RoleEntity;
import in.siddharth.mapper.RoleMapper;
import in.siddharth.repo.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
  	private RoleRepository roleRepository;

	@Override
	public RoleDto createRole(RoleDto roleDto) {
		RoleEntity entity = RoleMapper.dtoToEntity(roleDto);
		RoleEntity savedEntity = roleRepository.save(entity);
		return RoleMapper.entityToDto(savedEntity);

	}

	@Override
	public List<RoleDto> getAllRoles() {
		List<RoleEntity> entities = roleRepository.findAll();
		return entities.stream().map(RoleMapper :: entityToDto).collect(Collectors.toList());
	}

	@Override
	public RoleDto getRoleById(Integer roleId) {
		RoleEntity entity = roleRepository.findById(roleId)
				.orElseThrow( () -> new RuntimeException("Role is not found with id :"+roleId));
		return RoleMapper.entityToDto(entity);
	}

	@Override
	public RoleDto getRoleByName(String roleName) {
		RoleEntity entity = roleRepository.findByRoleName(roleName);
		return RoleMapper.entityToDto(entity);
	}

	@Override
	public RoleDto updateRole(Integer roleId, RoleDto roleDto) {
		RoleEntity existingEntity = roleRepository.findById(roleId)
				.orElseThrow( () -> new RuntimeException("Role is not found with id :"+ roleId));
		
		existingEntity.setRoleName(roleDto.getRoleName());
		RoleEntity savedEntity = roleRepository.save(existingEntity);
		return RoleMapper.entityToDto(savedEntity);

	}

	@Override
	public RoleDto deleteRole(Integer roleId, Integer updatedBy) {
		RoleEntity existingEntity = roleRepository.findById(roleId)
				.orElseThrow( () -> new RuntimeException("Role is not found with id :"+ roleId));
		
		if(existingEntity.getRoleName() != null ) {
			existingEntity.setActive(false);
			existingEntity.setUpdatedBy(updatedBy);
		}
		roleRepository.save(existingEntity);
		return RoleMapper.entityToDto(existingEntity);
	}

}
