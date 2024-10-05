package com.restart.account.mapper;

import com.restart.account.dto.CustomerDto;
import com.restart.account.dto.UpdateDto;
import com.restart.account.entity.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {

    public CustomerDto entityToDto(Customer customer, CustomerDto customerDto){
            customerDto.setName(customer.getName());
            customerDto.setEmail(customer.getEmail());
            customerDto.setMobileNo(customer.getMobileNo());
            return customerDto;
    }

    public Customer dtoToCustomer(CustomerDto customerDto, Customer customer){
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setMobileNo(customerDto.getMobileNo());
        return customer;
    }

    public void mapToCustomer(UpdateDto updateDto, Customer customer){
        customer.setName(updateDto.getName());
        customer.setEmail(updateDto.getEmail());

    }
}
