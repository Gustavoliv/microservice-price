package com.inditex.ecommerce.prices.infraestructure.exception;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class NotFoundExceptionTest {
    
    @Test
    void testConstructor() {
        String expectedMessage = "Not found exception message";
        NotFoundException exception = new NotFoundException(expectedMessage);
        
        assertEquals(expectedMessage, exception.getMessage());
    }
}
