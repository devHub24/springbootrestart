package com.restart.account.exceptions;

import com.restart.account.constants.AccountsConstants;
import com.restart.account.dto.ErrorDto;
import org.springframework.boot.context.properties.bind.validation.ValidationErrors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler  {

    private String path="";
    private String message="";
    private HttpStatus status= HttpStatus.ACCEPTED;


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleValidationErrors(MethodArgumentNotValidException ex) {
       /* List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
*/
       List<FieldError>errors=ex.getBindingResult().getFieldErrors();
       Map<String,String> result= new HashMap<>();
       for(FieldError err:errors){
           result.put(err.getField(),err.getDefaultMessage());
       }
       // return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }

    @ExceptionHandler(CustomerExceptions.class)
    public ResponseEntity handleExceptions(CustomerExceptions customerExceptions, WebRequest request){

        switch(customerExceptions.getError().name()){

            case "CUSTOMER_ALREADY_EXISTS":{
                message=customerExceptions.getMessage();
                path=request.getDescription(false);
                status=HttpStatus.BAD_REQUEST;
                break;
            }

            case "CUSTOMER_NOT_FOUND":{
                message= customerExceptions.getMessage();
                path= request.getDescription(false);
                status=HttpStatus.NOT_FOUND;
                break;
            }

            case "ACCOUNT_NOT_FOUND":{
                message=customerExceptions.getMessage();
                path=request.getDescription(false);
                status=HttpStatus.NOT_FOUND;
                break;
            }
        }

        return new ResponseEntity<>(new ErrorDto(path,status,message,LocalDateTime.now()),HttpStatus.BAD_REQUEST);
    }
}
