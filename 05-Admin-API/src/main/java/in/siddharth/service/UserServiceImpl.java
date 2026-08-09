package in.siddharth.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.siddharth.dto.UserDto;
import in.siddharth.entity.UserEntity;
import in.siddharth.mapper.UserMapper;
import in.siddharth.repo.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDto createUser(UserDto userDto) {
		UserEntity entity = UserMapper.dtoToEntity(userDto);
		entity.setCreatedBy(userDto.getCreatedBy());
		UserEntity savedEntity = userRepository.save(entity);
		return UserMapper.entityToDto(savedEntity);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<UserEntity> entities = userRepository.findAll();
		return entities.stream().map(UserMapper :: entityToDto).collect(Collectors.toList());
	}

	@Override
	public UserDto getUserById(Integer userId) {
		UserEntity entity = userRepository.findById(userId)
				.orElseThrow( () -> new RuntimeException("User is Not Found : " + userId));
		return UserMapper.entityToDto(entity);
	}

	@Override
	public UserDto getUserByEmail(String email) {
		UserEntity entity = userRepository.findByEmail(email);
		return UserMapper.entityToDto(entity);
	}

	@Override
	public UserDto updateUser(Integer userId, UserDto userDto) {
		UserEntity entity = userRepository.findById(userId)
				.orElseThrow( () -> new RuntimeException("User is Not Found : " + userId));
		entity.setName(userDto.getName());
		entity.setPhno(userDto.getPhno());
		entity.setPwd(userDto.getPwd());
		
		UserEntity savedEntity = userRepository.save(entity);
		return UserMapper.entityToDto(savedEntity);
	}

	@Override
	public List<UserDto> getUsersByRoleId(Integer roleId) {
		List<UserEntity> entities = userRepository.findByRolesRoleId(roleId);
		return  entities.stream().map(UserMapper :: entityToDto).collect(Collectors.toList());
	}

	@Override
	public UserDto deleteUser(Integer userId, Integer updatedBy) {
		UserEntity existingEntity = userRepository.findById(userId)
				.orElseThrow( () -> new RuntimeException("User is not Found"+userId));
		if(existingEntity.getEmail()!=null) {
			existingEntity.setActive(false);
			existingEntity.setUpdatedBy(updatedBy);
		}
		UserEntity savedEntity = userRepository.save(existingEntity);
		return UserMapper.entityToDto(savedEntity);
	}

}
