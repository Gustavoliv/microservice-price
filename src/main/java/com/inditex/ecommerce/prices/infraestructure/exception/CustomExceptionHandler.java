package com.inditex.ecommerce.prices.infraestructure.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.inditex.ecommerce.prices.infraestructure.rest.constants.Constant;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value()));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
        
        String parametro = ex.getName();
        String formatoCorrecto = "";
    
        switch (parametro) {
            case "brandId":
                formatoCorrecto = Constant.FORMAT_BRAND;
                break;
            case "productId":
                formatoCorrecto = Constant.FORMAT_PRODUCT;
                break;
            case "date":
                formatoCorrecto = Constant.FORMAT_DATE;
                break;
            default:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(Constant.INCORRECT_PARAM, HttpStatus.BAD_REQUEST.value()));
        }

        @SuppressWarnings("null")
        String message = "The '" + parametro + "' parameter has an incorrect format '" + ex.getValue().toString()
                + "'. Correct format: '" + formatoCorrecto +"'";

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(message, HttpStatus.BAD_REQUEST.value()));
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(Constant.HTTP_INTERNAL_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value()));
    }
}
