package com.inditex.ecommerce.prices.domain.port.in;

import com.inditex.ecommerce.prices.domain.model.Price;

public interface PriceDomainRepository {
    
    Price getPriceByBrandProductDate(Price price);
}
