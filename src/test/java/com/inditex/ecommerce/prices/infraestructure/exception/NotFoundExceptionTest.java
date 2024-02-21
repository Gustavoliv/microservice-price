package com.inditex.ecommerce.prices.infraestructure.exception;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.inditex.ecommerce.prices.infraestructure.rest.constants.Constant;

class NotFoundExceptionTest {
    
    @Test
    void testConstructor() {
        String expectedMessage = Constant.PRICE_NOT_FOUND;
        NotFoundException exception = new NotFoundException(expectedMessage);
        
        assertEquals(expectedMessage, exception.getMessage());
    }
}
