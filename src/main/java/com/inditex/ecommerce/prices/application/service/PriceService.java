package com.inditex.ecommerce.prices.application.service;

import java.util.Optional;

import com.inditex.ecommerce.prices.domain.model.Price;
import com.inditex.ecommerce.prices.domain.model.dto.PriceDTO;

public interface PriceService {

    Optional<PriceDTO> getPriceByBrandProductDate(Price priceSearch);
    
}
