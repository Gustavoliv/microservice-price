package com.inditex.ecommerce.prices.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Price {
    
    private Brand brand;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long priceList;
    private Product product;
    private Integer priority;
    private BigDecimal price;
    private String curr;
}
