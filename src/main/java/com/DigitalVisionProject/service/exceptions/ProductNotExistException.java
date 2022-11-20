package com.DigitalVisionProject.service.exceptions;

public class ProductNotExistException extends IllegalArgumentException{
    public ProductNotExistException(String msg) {
        super(msg);
    }
}
