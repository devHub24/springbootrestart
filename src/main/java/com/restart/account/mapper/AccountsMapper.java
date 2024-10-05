package com.restart.account.mapper;

import com.restart.account.dto.AccountsDto;
import com.restart.account.dto.UpdateDto;
import com.restart.account.entity.Accounts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Provider;

@Service
public class AccountsMapper {


    public AccountsDto entityToDto(Accounts accounts, AccountsDto accountsDto){
        accountsDto.setAccountNumber(accounts.getAccountNumber());
        accountsDto.setAccountType(accounts.getAccountType());
        accountsDto.setBranchAddress(accounts.getBranchAddress());

        return accountsDto;
    }

    public Accounts dtoToEntity(AccountsDto accountsDto,Accounts accounts){
        accounts.setAccountNumber(accountsDto.getAccountNumber());
        accounts.setAccountType(accountsDto.getAccountType());
        accounts.setBranchAddress(accountsDto.getBranchAddress());

        return accounts;
    }

    public void mapToAccounts(UpdateDto updateDto,Accounts accounts){
        accounts.setAccountType(updateDto.getAccountType());
        accounts.setBranchAddress(updateDto.getBranchAddress());

    }


}
