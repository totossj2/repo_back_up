package com.uade.tpo.cars_e_commerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Usuario incorrecto")
public class UserWrongUsernameExcpetion {
    
}
