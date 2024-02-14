package com.inditex.ecommerce.prices.infraestructure.rest.controller;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.inditex.ecommerce.prices.domain.model.dto.PriceDTO;
import com.inditex.ecommerce.prices.infraestructure.rest.constants.Constant;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.constraints.NotBlank;

@Api(value = "API GET prices")
public interface PriceController {

    @ApiOperation(value = "Get prices by date, brandId and productId")
    @ResponseStatus(HttpStatus.ACCEPTED)
    ResponseEntity<PriceDTO> getPricesForCondition(@RequestParam("date") @DateTimeFormat(pattern = Constant.FORMAT_DATE) LocalDateTime date,
                                @RequestParam("productId") @NotBlank(message = Constant.EMPTY_PRODUCT_FIELD) Long productId,
                                @RequestParam("brandId") @NotBlank(message = Constant.EMPTY_BRAND_FIELD) int brandId);
}
