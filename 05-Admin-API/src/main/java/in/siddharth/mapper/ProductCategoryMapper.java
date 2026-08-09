package in.siddharth.mapper;

import org.modelmapper.ModelMapper;

import in.siddharth.dto.ProductCategoryDto;
import in.siddharth.entity.ProductCategoryEntity;

public class ProductCategoryMapper {
	
	public static final ModelMapper mapper = new ModelMapper();
	
	public static ProductCategoryDto entityToDto(ProductCategoryEntity productCategoryEntity) {
		return mapper.map(productCategoryEntity, ProductCategoryDto.class);
	}
	
	public static ProductCategoryEntity dtoToEntity(ProductCategoryDto productCategoryDto) {
		return mapper.map(productCategoryDto, ProductCategoryEntity.class);
	}

}
