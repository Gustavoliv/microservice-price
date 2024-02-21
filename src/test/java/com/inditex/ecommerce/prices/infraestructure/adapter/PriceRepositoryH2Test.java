package com.inditex.ecommerce.prices.infraestructure.adapter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.inditex.ecommerce.prices.domain.model.Brand;
import com.inditex.ecommerce.prices.domain.model.Price;
import com.inditex.ecommerce.prices.domain.model.Product;
import com.inditex.ecommerce.prices.infraestructure.entity.PriceEntity;
import com.inditex.ecommerce.prices.infraestructure.exception.NotFoundException;
import com.inditex.ecommerce.prices.infraestructure.rest.mapper.PriceMapper;

class PriceRepositoryH2Test {
        
        private PriceRepository priceRepository;
        private PriceMapper priceMapper;
        private PriceRepositoryH2 priceRepositoryH2;

        @BeforeEach
        void setUp() {
                priceRepository = mock(PriceRepository.class);
                priceMapper = mock(PriceMapper.class);
                priceRepositoryH2 = new PriceRepositoryH2(priceRepository, priceMapper);
        }

        @Test
        void testGetPriceByBrandProductDate() {
                
                Price searchPrice = Price.builder()
                        .startDate(LocalDateTime.of(2020, 6, 14, 10, 0, 0))
                        .endDate(LocalDateTime.of(2020, 6, 14, 10, 0,0))
                        .brand(new Brand(1))
                        .product(new Product(35455L))
                        .build();

                PriceEntity expectedPriceEntity = PriceEntity.builder()
                        .brandId(1)
                        .productId(35455L)
                        .startDate(LocalDateTime.of(2020, 6, 14, 0, 0, 0))
                        .endDate(LocalDateTime.of(2020, 12, 31, 23, 59,59))
                        .priceList(1L)
                        .price(BigDecimal.valueOf(35.50).setScale(2, RoundingMode.HALF_UP))
                        .build();

                Price expectedPrice = Price.builder()
                        .brand(new Brand(1))
                        .product(new Product(35455L))
                        .startDate(LocalDateTime.of(2020, 6, 14, 0, 0, 0))
                        .endDate(LocalDateTime.of(2020, 12, 31, 23, 59,59))
                        .priceList(1L)
                        .priceValue(BigDecimal.valueOf(35.50).setScale(2, RoundingMode.HALF_UP))
                        .build();

                when(priceRepository.findFirstByStartDateBeforeAndEndDateAfterAndBrandIdAndProductIdOrderByPriorityDesc(
                        searchPrice.getStartDate(), searchPrice.getEndDate(), searchPrice.getBrand().getBrandId(),
                        searchPrice.getProduct().getProductId()))
                        .thenReturn(Optional.of(expectedPriceEntity));

                when(priceMapper.toPrice(expectedPriceEntity)).thenReturn(expectedPrice);

                Price result = priceRepositoryH2.getPriceByBrandProductDate(searchPrice);

                assertEquals(expectedPrice, result);
                verify(priceRepository, times(1)).findFirstByStartDateBeforeAndEndDateAfterAndBrandIdAndProductIdOrderByPriorityDesc(
                                searchPrice.getStartDate(), searchPrice.getEndDate(), searchPrice.getBrand().getBrandId(),
                                searchPrice.getProduct().getProductId());
                verify(priceMapper, times(1)).toPrice(expectedPriceEntity);
        }

        @Test
        void testGetPriceByBrandProductDate_NotFound() {
                
                Price searchPrice = Price.builder()
                        .startDate(LocalDateTime.of(2020, 6, 14, 10, 0, 0))
                        .endDate(LocalDateTime.of(2020, 6, 14, 10, 0,0))
                        .brand(new Brand(1))
                        .product(new Product(35455L))
                        .build();

                when(priceRepository.findFirstByStartDateBeforeAndEndDateAfterAndBrandIdAndProductIdOrderByPriorityDesc(
                        searchPrice.getStartDate(), searchPrice.getEndDate(), searchPrice.getBrand().getBrandId(),
                        searchPrice.getProduct().getProductId()))
                        .thenReturn(Optional.empty());

                assertThrows(NotFoundException.class, () -> {
                        priceRepositoryH2.getPriceByBrandProductDate(searchPrice);
                });

                verify(priceRepository, times(1)).findFirstByStartDateBeforeAndEndDateAfterAndBrandIdAndProductIdOrderByPriorityDesc(
                        searchPrice.getStartDate(), searchPrice.getEndDate(), searchPrice.getBrand().getBrandId(),
                        searchPrice.getProduct().getProductId());
        }
}
