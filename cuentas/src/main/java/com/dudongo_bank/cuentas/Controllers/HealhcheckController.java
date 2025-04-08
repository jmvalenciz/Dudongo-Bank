package com.dudongo_bank.cuentas.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealhcheckController {

    @GetMapping("")
    public boolean healthcheck(){
        return true;
    }
}
