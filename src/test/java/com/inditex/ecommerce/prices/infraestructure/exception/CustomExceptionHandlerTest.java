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
        NotFoundException ex = new NotFoundException(Constant.PRICE_NOT_FOUND);

        ResponseEntity<ErrorResponse> response = customExceptionHandler.handleCustomException(ex);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(Constant.PRICE_NOT_FOUND, response.getBody().getMessage());
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getBody().getStatusCode());
    }

    @SuppressWarnings("null")
    @Test
    void testHandleMethodArgumentTypeMismatch_Brand() {
        MethodArgumentTypeMismatchException exception = mock(MethodArgumentTypeMismatchException.class);
        when(exception.getName()).thenReturn("brandId");
        when(exception.getValue()).thenReturn("invalidValue");

        ResponseEntity<ErrorResponse> response = customExceptionHandler.handleMethodArgumentTypeMismatch(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("The 'brandId' parameter has an incorrect format 'invalidValue'. Correct format: '"+Constant.FORMAT_BRAND+"'", response.getBody().getMessage());
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getBody().getStatusCode());
    }

    @SuppressWarnings("null")
    @Test
    void testHandleMethodArgumentTypeMismatch_Product() {
        MethodArgumentTypeMismatchException exception = mock(MethodArgumentTypeMismatchException.class);
        when(exception.getName()).thenReturn("productId");
        when(exception.getValue()).thenReturn("invalidValue");

        ResponseEntity<ErrorResponse> response = customExceptionHandler.handleMethodArgumentTypeMismatch(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("The 'productId' parameter has an incorrect format 'invalidValue'. Correct format: '"+Constant.FORMAT_PRODUCT+"'", response.getBody().getMessage());
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getBody().getStatusCode());
    }

    @SuppressWarnings("null")
    @Test
    void testHandleMethodArgumentTypeMismatch_Date() {
        MethodArgumentTypeMismatchException exception = mock(MethodArgumentTypeMismatchException.class);
        when(exception.getName()).thenReturn("date");
        when(exception.getValue()).thenReturn("invalidValue");

        ResponseEntity<ErrorResponse> response = customExceptionHandler.handleMethodArgumentTypeMismatch(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("The 'date' parameter has an incorrect format 'invalidValue'. Correct format: '"+Constant.FORMAT_DATE+"'", response.getBody().getMessage());
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getBody().getStatusCode());
    }

    @SuppressWarnings("null")
    @Test
    void testHandleGenericException() {
        Exception exception = new Exception(Constant.HTTP_INTERNAL_ERROR);

        ResponseEntity<ErrorResponse> response = customExceptionHandler.handleGenericException(exception);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(Constant.HTTP_INTERNAL_ERROR, response.getBody().getMessage());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getBody().getStatusCode());
    }
}
