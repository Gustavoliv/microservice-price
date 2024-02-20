package com.inditex.ecommerce.prices.infraestructure.adapter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.inditex.ecommerce.prices.infraestructure.entity.PriceEntity;

class PriceRepositoryTest {

    @Test
    void test() {
        
        PriceEntity expectedPriceEntity = new PriceEntity();
        LocalDateTime currentDateStart = LocalDateTime.now().minusHours(1);
        LocalDateTime currentDateEnd = LocalDateTime.now().plusHours(1);
        int brandId = 1;
        Long productId = 123L;

        // Mock del repositorio
        PriceRepository priceRepositoryMock = mock(PriceRepository.class);
        when(priceRepositoryMock.findFirstByStartDateBeforeAndEndDateAfterAndBrandIdAndProductIdOrderByPriorityDesc(
                any(LocalDateTime.class), any(LocalDateTime.class), any(int.class), any(Long.class)))
                .thenReturn(Optional.of(expectedPriceEntity));

        // Act
        Optional<PriceEntity> result = priceRepositoryMock
                .findFirstByStartDateBeforeAndEndDateAfterAndBrandIdAndProductIdOrderByPriorityDesc(currentDateStart,
                        currentDateEnd, brandId, productId);

        // Assert
        assertEquals(expectedPriceEntity, result.get());
    }
}
    