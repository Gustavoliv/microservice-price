package com.inditex.ecommerce.prices.infraestructure.rest.controller.impl;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inditex.ecommerce.prices.application.service.PriceService;
import com.inditex.ecommerce.prices.domain.model.Brand;
import com.inditex.ecommerce.prices.domain.model.Price;
import com.inditex.ecommerce.prices.domain.model.Product;
import com.inditex.ecommerce.prices.domain.model.dto.PriceDTO;
import com.inditex.ecommerce.prices.infraestructure.rest.constants.Constant;
import com.inditex.ecommerce.prices.infraestructure.rest.controller.PriceController;

import jakarta.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/ecommerce")
public class PriceControllerImpl implements PriceController{
    
    private final PriceService priceService;

    public PriceControllerImpl(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping("/prices/price")
    public ResponseEntity<PriceDTO> getPricesForCondition(@RequestParam("date") @DateTimeFormat(pattern = Constant.FORMAT_DATE) LocalDateTime date,
                                @RequestParam("productId") @NotBlank(message = Constant.EMPTY_PRODUCT_FIELD) Long productId,
                                @RequestParam("brandId") @NotBlank(message = Constant.EMPTY_BRAND_FIELD) int brandId) {

        Price priceRequest = Price.builder()
            .brand(new Brand(brandId))
            .product(new Product(productId))
            .startDate(date)
            .endDate(date)
            .build();

        log.info("Price: PriceController.getPricesForCondition  date: {}, productId: {}, brandId: {}", date, productId, brandId);
        PriceDTO prices = priceService.getPriceByBrandProductDate(priceRequest);

        return new ResponseEntity<>(prices, HttpStatus.OK);
    }
}
