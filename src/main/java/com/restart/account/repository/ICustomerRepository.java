package com.restart.account.repository;

import com.restart.account.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer,Long> {

    public Optional<Customer> findByMobileNo(String mobileNo);
}
