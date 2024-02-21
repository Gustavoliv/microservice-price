package com.inditex.ecommerce.prices.application.service;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.inditex.ecommerce.prices.domain.model.Brand;
import com.inditex.ecommerce.prices.domain.model.Price;
import com.inditex.ecommerce.prices.domain.model.Product;
import com.inditex.ecommerce.prices.domain.model.dto.PriceDTO;

@SpringBootTest
@AutoConfigureMockMvc
class PriceServiceImplTest {

    @Autowired
    private PriceServiceImpl priceService;
        
    @Test
    void servicePrice_whenExists_shouldReturnPriceTest01() {
        
        Price searchPrice = Price.builder()
                        .startDate(LocalDateTime.of(2020, 6, 14, 10, 0, 0))
                        .endDate(LocalDateTime.of(2020, 6, 14, 10, 0,0))
                        .brand(new Brand(1))
                        .product(new Product(35455L))
                        .build();
                        
        PriceDTO expectedPriceDto = PriceDTO.builder()
                        .brandId(1)
                        .productId(35455L)
                        .startDate(LocalDateTime.of(2020, 6, 14, 0, 0, 0))
                        .endDate(LocalDateTime.of(2020, 12, 31, 23, 59,59))
                        .priceList(1L)
                        .price(BigDecimal.valueOf(35.50).setScale(2, RoundingMode.HALF_UP))
                        .build();
                        
        Optional<PriceDTO> result = priceService.getPriceByBrandProductDate(searchPrice);

        assertTrue(result.isPresent());
        assertEquals(result.get(), expectedPriceDto);
    }
        
    @Test
    void servicePrice_whenExists_shouldReturnPriceTest02() {
        
        Price searchPrice = Price.builder()
                        .startDate(LocalDateTime.of(2020, 6, 14, 16, 0, 0))
                        .endDate(LocalDateTime.of(2020, 6, 14, 16, 0,0))
                        .brand(new Brand(1))
                        .product(new Product(35455L))
                        .build();

        PriceDTO expectedPriceDto = PriceDTO.builder()
                        .brandId(1)
                        .productId(35455L)
                        .startDate(LocalDateTime.of(2020, 6, 14, 15, 0, 0))
                        .endDate(LocalDateTime.of(2020, 06, 14, 18, 30,0))
                        .priceList(2L)
                        .price(BigDecimal.valueOf(25.45).setScale(2, RoundingMode.HALF_UP))
                        .build();

        Optional<PriceDTO> result = priceService.getPriceByBrandProductDate(searchPrice);

        assertTrue(result.isPresent());
        assertEquals(result.get(), expectedPriceDto);
    }
        
    @Test
    void servicePrice_whenExists_shouldReturnPriceTest03() {
        
        Price searchPrice = Price.builder()
                        .startDate(LocalDateTime.of(2020, 6, 14, 21, 0, 0))
                        .endDate(LocalDateTime.of(2020, 6, 14, 21, 0,0))
                        .brand(new Brand(1))
                        .product(new Product(35455L))
                        .build();

        PriceDTO expectedPriceDto = PriceDTO.builder()
                        .brandId(1)
                        .productId(35455L)
                        .startDate(LocalDateTime.of(2020, 6, 14, 0, 0, 0))
                        .endDate(LocalDateTime.of(2020, 12, 31, 23, 59,59))
                        .priceList(1L)
                        .price(BigDecimal.valueOf(35.50).setScale(2, RoundingMode.HALF_UP))
                        .build();

        Optional<PriceDTO> result = priceService.getPriceByBrandProductDate(searchPrice);

        assertTrue(result.isPresent());
        assertEquals(result.get(), expectedPriceDto);
    }
        
    @Test
    void servicePrice_whenExists_shouldReturnPriceTest04() {

        Price searchPrice = Price.builder()
                        .startDate(LocalDateTime.of(2020, 6, 15, 10, 0, 0))
                        .endDate(LocalDateTime.of(2020, 6, 15, 10, 0,0))
                        .brand(new Brand(1))
                        .product(new Product(35455L))
                        .build();

        PriceDTO expectedPriceDto = PriceDTO.builder()
                        .brandId(1)
                        .productId(35455L)
                        .startDate(LocalDateTime.of(2020, 6, 15, 0, 0, 0))
                        .endDate(LocalDateTime.of(2020, 6, 15, 11, 0,0))
                        .priceList(3L)
                        .price(BigDecimal.valueOf(30.50).setScale(2, RoundingMode.HALF_UP))
                        .build();

        Optional<PriceDTO> result = priceService.getPriceByBrandProductDate(searchPrice);

        assertTrue(result.isPresent());
        assertEquals(result.get(), expectedPriceDto);
    }
        
    @Test
    void servicePrice_whenExists_shouldReturnPriceTest05() {
        
        Price searchPrice = Price.builder()
                        .startDate(LocalDateTime.of(2020, 6, 16, 21, 0, 0))
                        .endDate(LocalDateTime.of(2020, 6, 16, 21, 0,0))
                        .brand(new Brand(1))
                        .product(new Product(35455L))
                        .build();

        PriceDTO expectedPriceDto = PriceDTO.builder()
                        .brandId(1)
                        .productId(35455L)
                        .startDate(LocalDateTime.of(2020, 6, 15, 16, 0, 0))
                        .endDate(LocalDateTime.of(2020, 12, 31, 23, 59,59))
                        .priceList(4L)
                        .price(BigDecimal.valueOf(38.95).setScale(2, RoundingMode.HALF_UP))
                        .build();

        Optional<PriceDTO> result = priceService.getPriceByBrandProductDate(searchPrice);

        assertTrue(result.isPresent());
        assertEquals(result.get(), expectedPriceDto);
    }
    
    @Test
    void servicePrice_whenGetSeveralResult_shouldGetHighestPriority() {

        Price searchPrice = Price.builder()
                        .startDate(LocalDateTime.of(2020, 6, 14, 17, 0, 0))
                        .endDate(LocalDateTime.of(2020, 6, 14, 17, 0,0))
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

        Optional<PriceDTO> result = priceService.getPriceByBrandProductDate(searchPrice);

        assertTrue(result.isPresent());
        assertEquals(result.get(), expectedPriceDto);
    }
    
    @Test
    void endpointPrice_whenNotExist_shouldReturnEmpty() {
        
        Price searchPrice = Price.builder()
                        .startDate(LocalDateTime.of(2010, 6, 16, 21, 0, 0))
                        .endDate(LocalDateTime.of(2010, 6, 16, 21, 0,0))
                        .brand(new Brand(1))
                        .product(new Product(35455L))
                        .build();

        Optional<PriceDTO> result = priceService.getPriceByBrandProductDate(searchPrice);

        assertTrue(result.isEmpty());
    }
}
    