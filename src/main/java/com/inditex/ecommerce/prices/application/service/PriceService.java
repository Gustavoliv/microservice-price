package com.inditex.ecommerce.prices.application.service;

import com.inditex.ecommerce.prices.domain.model.Price;
import com.inditex.ecommerce.prices.domain.model.dto.PriceDTO;

public interface PriceService {

    PriceDTO getPriceByBrandProductDate(Price priceSearch);
    
}
