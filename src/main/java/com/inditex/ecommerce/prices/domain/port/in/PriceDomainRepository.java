package com.inditex.ecommerce.prices.domain.port.in;

import java.util.Optional;

import com.inditex.ecommerce.prices.domain.model.Price;

public interface PriceDomainRepository {
    
    Optional<Price> getPriceByBrandProductDate(Price price);
}
