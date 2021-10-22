package dev.alexferreira.sampleapi.modules.product.dto.mapper;

import dev.alexferreira.sampleapi.modules.product.dto.ProdutoDTO;
import dev.alexferreira.sampleapi.modules.product.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ProductMapper {

	ProdutoDTO toDTO(Product product);

	@Mapping(target = "id", ignore = true)
	Product toEntity(ProdutoDTO dto);
}
