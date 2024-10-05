package com.restart.account.service;

import com.restart.account.constants.AccountsConstants;
import com.restart.account.dto.AccountsDto;
import com.restart.account.dto.AggregatedDetailsDto;
import com.restart.account.dto.CustomerDto;
import com.restart.account.dto.UpdateDto;
import com.restart.account.entity.Accounts;
import com.restart.account.entity.Customer;
import com.restart.account.exceptions.CustomerExceptions;
import com.restart.account.mapper.AccountsMapper;
import com.restart.account.mapper.CustomerMapper;
import com.restart.account.repository.IAccountsRepository;
import com.restart.account.repository.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static com.restart.account.exceptions.CustomerExceptions.CustomerErrors.*;
import java.util.Random;

@Service
public class ImpAccountService implements IAccountService{

    @Autowired
    private IAccountsRepository accountRepo;
    @Autowired
    private ICustomerRepository customerRepository;
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private AccountsMapper accountsMapper;


    /**
     *
     * @param customerDto
     */

    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer =customerMapper.dtoToCustomer(customerDto,new Customer());
        if(customerRepository.findByMobileNo(customerDto.getMobileNo()).isPresent()){
            throw new CustomerExceptions(CUSTOMER_ALREADY_EXISTS,CUSTOMER_ALREADY_EXISTS.name());
        }
        Customer savedCustomer=customerRepository.save(customer);
        Accounts account= createNewAccount(savedCustomer);
        Accounts savedAccount=accountRepo.save(account);
    }

    /**
     *
     * @param mobileNo
     * @return customerDto
     */

    @Override
    public AggregatedDetailsDto getCustomerByMobileNo(String mobileNo) {
        Customer customer=customerRepository.findByMobileNo(mobileNo).orElseThrow(()->
                new CustomerExceptions(CustomerExceptions.CustomerErrors.CUSTOMER_NOT_FOUND,String.format("Customer not found with the mobile no: %s",mobileNo))
        );
        Accounts accounts= accountRepo.findByCustomerId(customer.getCustomerId()).orElseThrow(()->
                new CustomerExceptions(ACCOUNT_NOT_FOUND,String.format("Account not found for the customer with mobile no:%s",mobileNo))
                );
        CustomerDto customerDto =customerMapper.entityToDto(customer,new CustomerDto());
        AccountsDto accountsDto=accountsMapper.entityToDto(accounts,new AccountsDto());
        AggregatedDetailsDto result= new AggregatedDetailsDto(customerDto,accountsDto);
        return result;
    }

    /**
     *
     * @param updateDto
     * @return
     */
    @Override
    public AggregatedDetailsDto updateCustomer(UpdateDto updateDto) {
        Customer customer=customerRepository.findByMobileNo(updateDto.getMobileNo()).orElseThrow(()->
                new CustomerExceptions(CUSTOMER_NOT_FOUND,String.format("Customer not found with mobile no:%s",updateDto.getMobileNo()))
                );

        customerMapper.mapToCustomer(updateDto,customer);
        Customer updatedCustomer= customerRepository.save(customer);
        CustomerDto resultCustomer= customerMapper.entityToDto(updatedCustomer,new CustomerDto());
        Accounts account=accountRepo.findById(updateDto.getAccountNumber()).orElseThrow(()->
                    new CustomerExceptions(ACCOUNT_NOT_FOUND,String.format("Account not found with number:%s",updateDto.getAccountNumber()))
                );

        accountsMapper.mapToAccounts(updateDto,account);
        Accounts updatedAccounts=accountRepo.save(account);
        AccountsDto resultAccount= accountsMapper.entityToDto(updatedAccounts,new AccountsDto());
        AggregatedDetailsDto result= new AggregatedDetailsDto(resultCustomer,resultAccount);
        return result;
    }

    /**
     *
     * @param mobileNo
     */
    @Override
    public void deleteCustomer(String mobileNo) {
        Customer customer= customerRepository.findByMobileNo(mobileNo).orElseThrow(()->
                new CustomerExceptions(CUSTOMER_NOT_FOUND,String.format("Customer not found with mobile no:%s",mobileNo))
                );
        Accounts account=accountRepo.findByCustomerId(customer.getCustomerId()).orElseThrow(()->
                new CustomerExceptions(ACCOUNT_NOT_FOUND,String.format("Account not found with customer of mobile no:%s",mobileNo))
                );
        customerRepository.delete(customer);
        accountRepo.delete(account);

    }

    /**
     *
     * @param customer
     * @return Accounts
     */
    private Accounts createNewAccount(Customer customer){

        Accounts account = new Accounts();
        account.setCustomerId(customer.getCustomerId());
        account.setAccountNumber(1000L+(new Random().nextInt(900000000))+customer.getCustomerId());
        account.setAccountType(AccountsConstants.SAVINGS);
        account.setBranchAddress(AccountsConstants.ADDRESS);
        return account;
    }
}
