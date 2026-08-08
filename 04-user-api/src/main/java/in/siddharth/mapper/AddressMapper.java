package in.siddharth.mapper;

import org.modelmapper.ModelMapper;

import in.siddharth.dto.ShippingAddressDto;
import in.siddharth.entity.ShippingAddressEntity;

public class AddressMapper {
	
	public static final ModelMapper modelMapper = new ModelMapper();
	
	public static ShippingAddressEntity dtoToEntity(ShippingAddressDto shippingAddressDto) {
		return modelMapper.map(shippingAddressDto, ShippingAddressEntity.class);
	}
	
	public static ShippingAddressDto entityToDto(ShippingAddressEntity shippingAddressEntity) {
		return modelMapper.map(shippingAddressEntity, ShippingAddressDto.class);
	}

}
