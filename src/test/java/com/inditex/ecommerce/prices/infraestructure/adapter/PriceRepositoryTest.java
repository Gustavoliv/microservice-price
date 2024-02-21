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

class PriceRepositoryTest {

        PriceRepository priceRepositoryMock;

        @BeforeEach
        void setUp() {
                priceRepositoryMock = mock(PriceRepository.class);
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

                when(priceRepositoryMock.findFirstByStartDateBeforeAndEndDateAfterAndBrandIdAndProductIdOrderByPriorityDesc(
                        searchPrice.getStartDate(), searchPrice.getEndDate(), searchPrice.getBrand().getBrandId(),
                        searchPrice.getProduct().getProductId()))
                        .thenReturn(Optional.of(expectedPriceEntity));

                Optional<PriceEntity> result = priceRepositoryMock
                        .findFirstByStartDateBeforeAndEndDateAfterAndBrandIdAndProductIdOrderByPriorityDesc(
                        searchPrice.getStartDate(), searchPrice.getEndDate(), searchPrice.getBrand().getBrandId(),
                        searchPrice.getProduct().getProductId()
                        );

                assertEquals(expectedPriceEntity, result.get());

                verify(priceRepositoryMock, times(1)).findFirstByStartDateBeforeAndEndDateAfterAndBrandIdAndProductIdOrderByPriorityDesc(
                                searchPrice.getStartDate(), searchPrice.getEndDate(), searchPrice.getBrand().getBrandId(),
                                searchPrice.getProduct().getProductId());
        }
}
