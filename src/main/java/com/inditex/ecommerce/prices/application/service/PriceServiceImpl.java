package com.inditex.ecommerce.prices.application.service;

import java.util.Optional;

import com.inditex.ecommerce.prices.application.mapper.PriceDtoMapper;
import com.inditex.ecommerce.prices.domain.model.Price;
import com.inditex.ecommerce.prices.domain.model.dto.PriceDTO;
import com.inditex.ecommerce.prices.domain.port.in.PriceDomainRepository;

public class PriceServiceImpl implements PriceService{

    private final PriceDomainRepository priceDomainRepository;
    private final PriceDtoMapper priceDtoMapper;

    public PriceServiceImpl(PriceDomainRepository priceDomainRepository, PriceDtoMapper priceDtoMapper){
        this.priceDomainRepository = priceDomainRepository;
        this.priceDtoMapper = priceDtoMapper;
    }

    @Override
    public Optional<PriceDTO> getPriceByBrandProductDate(Price priceSearch) {

        Optional<Price> price = this.priceDomainRepository.getPriceByBrandProductDate(priceSearch);

        return price.map(this.priceDtoMapper::toPriceDto);
    }
}
