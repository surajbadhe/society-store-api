package com.tvf.societystore.mapper;

import com.tvf.societystore.dto.product.ProductDTO;
import com.tvf.societystore.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "wholesalerId", source = "wholesaler.id")
    ProductDTO toDto(Product product);
}
