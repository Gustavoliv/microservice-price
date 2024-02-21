package com.inditex.ecommerce.prices.application.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.inditex.ecommerce.prices.application.mapper.PriceDtoMapper;
import com.inditex.ecommerce.prices.domain.model.Brand;
import com.inditex.ecommerce.prices.domain.model.Price;
import com.inditex.ecommerce.prices.domain.model.Product;
import com.inditex.ecommerce.prices.domain.model.dto.PriceDTO;
import com.inditex.ecommerce.prices.domain.port.in.PriceDomainRepository;

class PriceServiceImplTest {
    
    @Mock
    private PriceDomainRepository priceDomainRepository;
    
    @Mock
    private PriceDtoMapper priceDtoMapper;
    
    @InjectMocks
    private PriceServiceImpl priceService;
    
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetPriceByBrandProductDate() {
        
        Price searchPrice = Price.builder()
                        .startDate(LocalDateTime.of(2020, 6, 14, 17, 0, 0))
                        .endDate(LocalDateTime.of(2020, 6, 14, 17, 0,0))
                        .brand(new Brand(1))
                        .product(new Product(35455L))
                        .build();
                
        Price returnPrice = Price.builder()
                        .startDate(LocalDateTime.of(2020, 6, 14, 15, 0, 0))
                        .endDate(LocalDateTime.of(2020, 6, 14, 18, 30,0))
                        .brand(new Brand(1))
                        .product(new Product(35455L))
                        .build();

        PriceDTO expectedPriceDto = PriceDTO.builder()
                        .brandId(1)
                        .productId(35455L)
                        .startDate(LocalDateTime.of(2020, 6, 14, 15, 0, 0))
                        .endDate(LocalDateTime.of(2020, 6, 14, 18, 30,0))
                        .priceList(2L)
                        .price(BigDecimal.valueOf(25.45).setScale(2, RoundingMode.HALF_UP))
                        .build();

        when(priceDomainRepository.getPriceByBrandProductDate(searchPrice)).thenReturn(returnPrice);
        when(priceDtoMapper.toPriceDto(returnPrice)).thenReturn(expectedPriceDto);

        PriceDTO result = priceService.getPriceByBrandProductDate(searchPrice);

        assertEquals(expectedPriceDto, result);
        verify(priceDomainRepository, times(1)).getPriceByBrandProductDate(searchPrice);
        verify(priceDtoMapper, times(1)).toPriceDto(returnPrice);
    }
}
    