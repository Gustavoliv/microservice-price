package com.inditex.ecommerce.prices.infraestructure.exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message){
        super(message);
    }
}
