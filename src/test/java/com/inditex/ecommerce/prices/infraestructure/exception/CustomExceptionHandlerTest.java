package com.inditex.ecommerce.prices.infraestructure.exception;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.inditex.ecommerce.prices.infraestructure.rest.constants.Constant;

class CustomExceptionHandlerTest {
    
    private CustomExceptionHandler customExceptionHandler;
    
    @BeforeEach
    public void setUp() {
        customExceptionHandler = new CustomExceptionHandler();
    }

    @SuppressWarnings("null")
    @Test
    void testHandleCustomException() {
        NotFoundException ex = new NotFoundException("Custom not found message");

        ResponseEntity<ErrorResponse> response = customExceptionHandler.handleCustomException(ex);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Custom not found message", response.getBody().getMessage());
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getBody().getStatusCode());
    }

    @SuppressWarnings("null")
    @Test
    void testHandleMethodArgumentTypeMismatch() {
        MethodArgumentTypeMismatchException ex = mock(MethodArgumentTypeMismatchException.class);
        when(ex.getName()).thenReturn("brandId");
        when(ex.getValue()).thenReturn("invalidValue");

        ResponseEntity<ErrorResponse> response = customExceptionHandler.handleMethodArgumentTypeMismatch(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("The 'brandId' parameter has an incorrect format 'invalidValue'. Correct format: '1'", response.getBody().getMessage());
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getBody().getStatusCode());
    }

    @SuppressWarnings("null")
    @Test
    void testHandleGenericException() {
        Exception ex = new Exception("Generic exception message");

        ResponseEntity<ErrorResponse> response = customExceptionHandler.handleGenericException(ex);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(Constant.HTTP_INTERNAL_ERROR, response.getBody().getMessage());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getBody().getStatusCode());
    }
}
