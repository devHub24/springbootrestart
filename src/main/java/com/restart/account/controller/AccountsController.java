package com.restart.account.controller;

import com.restart.account.constants.AccountsConstants;
import com.restart.account.dto.*;
import com.restart.account.service.IAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name="Accounts REST APIs",
description="REST APIs for CRUD operation on aAccount in eazybank"
)
@RestController
@RequestMapping(value = "/accounts",produces ={MediaType.APPLICATION_JSON_VALUE})
public class AccountsController {

    @Autowired
    private IAccountService accountService;

    @Operation(summary = "Create REST API",
            description="API to create new Account with the new Customer")
    @ApiResponse(
            responseCode = "201",
            description = "HTTP response Created",
            content = @Content(
                    schema = @Schema(implementation = ErrorDto.class)
            )
    )
    @PostMapping("/newCustomer")
    public ResponseEntity newAccount(@Valid @RequestBody CustomerDto customerDto){
        accountService.createAccount(customerDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountsConstants.STATUS_201,AccountsConstants.MESSAGE_201));
    }

    @Operation(summary="Fetch API by Mobile",
        description = "REST API to fetch customer and accounts detail by customer Mobile no"
    )
    @ApiResponse(
            responseCode = "302",
            description = "HTTP Status FOUND"
    )
    @GetMapping("/getByMobileNo")
    public ResponseEntity<AggregatedDetailsDto> getCustomerByMobileNo(@Pattern(regexp = "(^$|[0-9]{10})",message="Invalid mobile number")
                                                                          @RequestParam("mobileNo")String mobileNo){
        AggregatedDetailsDto result= accountService.getCustomerByMobileNo(mobileNo);
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(result);
    }

    @Operation(summary = "REST API for Update",
        description="REST API to update the customer with account details"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status Ok"
    )
    @PutMapping("/updateAccount")
    public ResponseEntity<AggregatedDetailsDto> updateAccount(@Valid @RequestBody UpdateDto updateDto){
        AggregatedDetailsDto result= accountService.updateCustomer(updateDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }

    @Operation(
            summary="REST API to Delete",
            description = "REST API to delete Customer or account by mobile no"
    )
    @ApiResponse(
            responseCode = "200",
            description="HTTP status Ok"
    )
    @DeleteMapping("/deleteByMobileNo")
    public ResponseEntity<ResponseDto> deleteAccount(@Pattern(regexp = "(^$|[0-9]{10})",message="Invalid mobile number")
                                                         @RequestParam("mobileNo")String mobileNo){
        accountService.deleteCustomer(mobileNo);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(AccountsConstants.STATUS_200,AccountsConstants.MESSAGE_200));
    }

    @GetMapping("/getTest")
    public ResponseEntity getTest(){
        return ResponseEntity.status(HttpStatus.OK).body("Hi there");
    }
}
