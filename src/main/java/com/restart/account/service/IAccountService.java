package com.restart.account.service;

import com.restart.account.dto.AccountsDto;
import com.restart.account.dto.AggregatedDetailsDto;
import com.restart.account.dto.CustomerDto;
import com.restart.account.dto.UpdateDto;

public interface IAccountService {

    public void createAccount(CustomerDto customerDto);
    public AggregatedDetailsDto getCustomerByMobileNo(String mobileNo);
    public AggregatedDetailsDto updateCustomer(UpdateDto updateDto);
    public void deleteCustomer(String mobileNo);
}
