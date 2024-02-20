package com.inditex.ecommerce.prices.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.inditex.ecommerce.prices.domain.model.Price;
import com.inditex.ecommerce.prices.domain.model.dto.PriceDTO;

@Mapper(componentModel = "spring")
public interface PriceDtoMapper {
    
    @Mapping(source = "brand.brandId", target = "brandId")
    @Mapping(source = "startDate", target = "startDate")
    @Mapping(source = "endDate", target = "endDate")
    @Mapping(source = "priceList", target = "priceList")
    @Mapping(source = "product.productId", target = "productId")
    @Mapping(source = "price", target = "price")
    PriceDTO toPriceDto(Price price);
}

