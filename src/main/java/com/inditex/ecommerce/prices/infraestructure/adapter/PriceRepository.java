package com.inditex.ecommerce.prices.infraestructure.adapter;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inditex.ecommerce.prices.infraestructure.entity.PriceEntity;

public interface PriceRepository extends JpaRepository<PriceEntity, Integer>{
    
    Optional<PriceEntity> 
        findFirstByStartDateBeforeAndEndDateAfterAndBrandIdAndProductIdOrderByPriorityDesc
        (LocalDateTime currentDateStart, LocalDateTime currentDateEnd, int brandId, Long productId);
}
