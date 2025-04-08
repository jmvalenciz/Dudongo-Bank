package com.dudongo_bank.cuentas.ErrorHandlers;

import com.dudongo_bank.cuentas.Models.TipoCuenta;

public class AccountTypeLimitExceededException extends BusinessException {
    public AccountTypeLimitExceededException(TipoCuenta tipoCuenta) {
        super(tipoCuenta.name());
    }
}
