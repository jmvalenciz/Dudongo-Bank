package com.dudongo_bank.cuentas.ErrorHandlers;

public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}
