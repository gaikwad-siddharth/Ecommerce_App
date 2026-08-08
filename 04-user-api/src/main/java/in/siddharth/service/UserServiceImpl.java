package in.siddharth.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.siddharth.dto.ResetPwdDto;
import in.siddharth.dto.ShippingAddressDto;
import in.siddharth.dto.UserDto;
import in.siddharth.entity.RoleEntity;
import in.siddharth.entity.ShippingAddressEntity;
import in.siddharth.entity.UserEntity;
import in.siddharth.mapper.AddressMapper;
import in.siddharth.mapper.UserMapper;
import in.siddharth.repo.RoleRepository;
import in.siddharth.repo.ShippingAddressRepository;
import in.siddharth.repo.UserRepository;

@Service
public class UserServiceImpl implements UserService {


	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ShippingAddressRepository shippingAddressRepository;

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private EmailService emailService;

	@Override
	public UserDto getUserByEmail(String email) {
		UserEntity userEntity = userRepository.findByEmail(email);

		if (userEntity != null) {
			return UserMapper.entityToDto(userEntity);
		}
		return null;
	}

	@Override
	public UserDto savedUser(UserDto userDto) {

		String randomPwd = genrateRandomPwd(5);

		userDto.setPwd(randomPwd);
		userDto.setPwdUpdated("NO");

		UserEntity userEntity = UserMapper.dtoToEntity(userDto);

		Set<RoleEntity> roleSet = new HashSet<>();

		RoleEntity role = roleRepository.findByRoleName(userDto.getRoleName());
		if (role != null) {
			roleSet.add(role);
		}else {
			throw new RuntimeException("Role not Found"+ userDto.getRoleName());
		}

		userEntity.setRoles(roleSet);

		UserEntity savedEntity = userRepository.save(userEntity);
		
		if(savedEntity != null ) {
			String emailBody = "Your account has been created. Your temporary password is: " + randomPwd;

			emailService.sendEmail(
			    "Account Created",
			    emailBody,
			    savedEntity.getEmail()
			);
		}

		return UserMapper.entityToDto(savedEntity);
	}

	private String genrateRandomPwd(int pwdLength) {

		Random random = new Random();

		String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789";

		StringBuffer buffer = new StringBuffer(pwdLength);

		for (int i = 0; i < pwdLength; i++) {
			int randomIndex = random.nextInt(chars.length());
			char charAt = chars.charAt(randomIndex);
			buffer.append(charAt);
		}
		return buffer.toString();
	}

	@Override
	public ResetPwdDto resetPwd(ResetPwdDto resetPwdDto) {

		UserEntity userEntity = userRepository.findByEmail(resetPwdDto.getEmail());

		if (userEntity != null) {

			if (resetPwdDto.getNewPwd().equals(resetPwdDto.getConfirmPwd())) {
				userEntity.setPwd(resetPwdDto.getNewPwd());
				userEntity.setPwdUpdated("YES");
				userRepository.save(userEntity);

				return resetPwdDto;
			}
		}
		return null;
	}

	@Override
	public UserDto login(String email, String pwd) {

	    UserEntity userEntity = userRepository.findByEmailAndPwd(email, pwd);

	    if (userEntity != null) {

	    	RoleEntity role = userEntity.getRoles().iterator().next();
	    	String roleName = role.getRoleName();

	        UserDto userDto = UserMapper.entityToDto(userEntity);

	        userDto.setRoleName(roleName);

	        return userDto;
	    }

	    return null;
	}

	@Override
	public ShippingAddressDto saveShippingAddress(Integer userId, ShippingAddressDto shippingAddressDto) {

		ShippingAddressEntity addressEntity = AddressMapper.dtoToEntity(shippingAddressDto);

		Optional<UserEntity> user = userRepository.findById(userId);
		if (user.isPresent()) {
			UserEntity userEntity = user.get();
			addressEntity.setUser(userEntity);
			ShippingAddressEntity savedAddrEntity = shippingAddressRepository.save(addressEntity);
			return AddressMapper.entityToDto(savedAddrEntity);
		}

		return null;
	}

	@Override
	public ShippingAddressDto deleteShippingAddress(Integer shippingAddressId) {
		Optional<ShippingAddressEntity> address = shippingAddressRepository.findById(shippingAddressId);
		if (address.isPresent()) {
			ShippingAddressEntity addressEntity = address.get();
			shippingAddressRepository.delete(addressEntity);
			return AddressMapper.entityToDto(addressEntity);
		}
		return null;
	}

}
