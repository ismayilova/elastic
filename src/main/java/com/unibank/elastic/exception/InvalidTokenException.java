package com.unibank.elastic.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.security.sasl.AuthenticationException;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED,  reason = "Acces denied to given token")

public class InvalidTokenException extends Exception {

    public InvalidTokenException(){
//        super("Access denied");
     System.out.println("WRONG token");
    }

    @Override
    public String getMessage() {
        return "Invalid token . Access denied to given token";
    }



}
