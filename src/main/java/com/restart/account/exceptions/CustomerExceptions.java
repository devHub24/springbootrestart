package com.restart.account.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class CustomerExceptions extends RuntimeException{

    public enum CustomerErrors{
        CUSTOMER_ALREADY_EXISTS,
        CUSTOMER_NOT_FOUND,
        ACCOUNT_NOT_FOUND;

    }
    private CustomerErrors error;
    private String message;




    public CustomerExceptions(CustomerErrors errors,String message){
       this.error=errors;
       this.message=message;

    }
}
