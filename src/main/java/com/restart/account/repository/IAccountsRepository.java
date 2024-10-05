package com.restart.account.repository;

import com.restart.account.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAccountsRepository extends JpaRepository<Accounts,Long> {

    public Optional<Accounts> findByCustomerId(Long customerId);
    public void deleteByCustomerId(Long customerId);
}
