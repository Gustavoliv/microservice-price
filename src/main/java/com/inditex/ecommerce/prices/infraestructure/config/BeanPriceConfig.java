package com.inditex.ecommerce.prices.infraestructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.inditex.ecommerce.prices.application.mapper.PriceDtoMapper;
import com.inditex.ecommerce.prices.application.service.PriceService;
import com.inditex.ecommerce.prices.application.service.PriceServiceImpl;
import com.inditex.ecommerce.prices.domain.port.in.PriceDomainRepository;

@Configuration
public class BeanPriceConfig {
    
    @Bean
    PriceService priceBeanService(final PriceDomainRepository priceDomainRepository, PriceDtoMapper priceDtoMapper){
        return new PriceServiceImpl(priceDomainRepository, priceDtoMapper);
    }
}
