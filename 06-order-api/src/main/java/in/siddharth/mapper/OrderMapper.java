package in.siddharth.mapper;

import org.modelmapper.ModelMapper;

import in.siddharth.dto.OrderDto;
import in.siddharth.entity.OrderEntity;

public class OrderMapper {
	
	public static final ModelMapper mapper = new ModelMapper();

	public static OrderDto entityToDto(OrderEntity orderEntity) {
		return mapper.map(orderEntity, OrderDto.class);
	}
	
	public static OrderEntity dtoToEntity(OrderDto orderDto) {
		return mapper.map(orderDto, OrderEntity.class);
	}

}
