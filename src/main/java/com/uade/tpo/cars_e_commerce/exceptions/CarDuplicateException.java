package com.uade.tpo.cars_e_commerce.exceptions;

public class CarDuplicateException extends Exception {

    private static final long serialVersionUID = 1L;

    public CarDuplicateException(String message) {
        super(message);
    }
}
