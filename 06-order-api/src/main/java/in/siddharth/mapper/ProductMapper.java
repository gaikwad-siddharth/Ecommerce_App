package in.siddharth.mapper;

import org.modelmapper.ModelMapper;

import in.siddharth.dto.ProductDto;
import in.siddharth.entity.ProductEntity;

public class ProductMapper {
	
	public static final ModelMapper mapper = new ModelMapper();

	public static ProductDto entityToDto(ProductEntity productEntity) {
		return mapper.map(productEntity, ProductDto.class);
	}
	
	public static ProductEntity dtoToEntity(ProductDto productDto) {
		return mapper.map(productDto, ProductEntity.class);
	}
	

}
