package com.DigitalVisionProject.service.exceptions;

public class CartItemNotExistException extends IllegalArgumentException {
    public CartItemNotExistException(String msg) {
        super(msg);
    }
}
