package com.inditex.ecommerce.prices.infraestructure.rest.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import com.inditex.ecommerce.prices.domain.model.Price;
import com.inditex.ecommerce.prices.infraestructure.entity.PriceEntity;

@Component
@Mapper(componentModel = "spring")
public interface PriceMapper {
    
    @Mapping(source = "brandId", target = "brand.brandId")
    @Mapping(source = "startDate", target = "startDate")
    @Mapping(source = "endDate", target = "endDate")
    @Mapping(source = "priceList", target = "priceList")
    @Mapping(source = "productId", target = "product.productId")
    @Mapping(source = "priority", target = "priority")
    @Mapping(source = "price", target = "priceValue")
    @Mapping(source = "curr", target = "curr")
    Price toPrice(PriceEntity priceEntity);
}
