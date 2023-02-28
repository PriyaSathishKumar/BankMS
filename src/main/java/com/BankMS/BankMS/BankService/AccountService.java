package com.BankMS.BankMS.BankService;

import com.BankMS.BankMS.BankEntity.AccountEntity;
import com.BankMS.BankMS.BankEntity.CustomerEntity;
import com.BankMS.BankMS.BankRepository.AccountRepository;
import com.BankMS.BankMS.BankRepository.CustomerRepository;
import com.BankMS.BankMS.dTO.CustomerAccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {
        @Autowired
        private AccountRepository repository;
        @Autowired
        private CustomerRepository customerRepository;
        @Autowired
        private CustomerService customerService;
        public ResponseEntity<?> createAccount(CustomerAccountDTO accountRequestDto,CustomerEntity  id) {
                CustomerEntity customer = customerService.getCustomerById(accountRequestDto.getCustId());
                if (customer == null) {
                        return ResponseEntity.badRequest().body("Customer not found");
                }
                AccountEntity account = new AccountEntity();
                //account.setAccountNumber(accountRequestDto.getAccountNumber());
                //account.setCustomer(customer);
                account.setBalance(accountRequestDto.getBalance());
                account.setAccType(accountRequestDto.getAccType());
                account.setStatus(accountRequestDto.getStatus());
                repository.save(account);
                return ResponseEntity.ok("Account created successfully");
        }
//        public String saveAccount(AccountEntity entity) {
//                 CustomerEntity customer=null;
//                CustomerEntity id = customerService.getCustomerById(customer.getCust_Id());
//                try {
//                        if (id == null) {
//                                throw new Exception("Enter valid Customer ID");
//                        }
//                        if (!customerRepository.existsById(id)) {
//                             return "Customer not found";
//                        }else {
//                                 repository.save(entity);
//                                return "Account Created Successfully";
//                        }
//                } catch (Exception e) {
//                        throw new RuntimeException(e);
//                }finally{
//                        return "Exit from Create Account";
//                }
//        }
        public List<AccountEntity> getAccounts() {
                return repository.findAll();
        }
        public AccountEntity getAccountById(int accountNumber) {

                return repository.findById(accountNumber).orElse(null);
        }
        public AccountEntity getAccountsByCustomerId(int cust_id) {

                return repository.findById(cust_id).orElse(null);
        }
        public String  deleteAccountById(int accountNumber) {
                repository.deleteById(accountNumber);
                return "Account- " + accountNumber + " is deleted";
        }
        public AccountEntity updateAccount(AccountEntity entity) {
                AccountEntity existingAccount = repository.findById(entity.getAccountNumber()).orElse(null);
                existingAccount.setAccType(entity.getAccType());
                existingAccount.setBalance(entity.getBalance());
                //existingAccount.setCustName(entity.getCustName());
                //existingAccount.setCust_id(entity.getCust_id());
                existingAccount.setStatus(entity.getStatus());
                return repository.save(existingAccount);
        }
        public List<CustomerAccountDTO> getAllAccountDetailsforCustomer(CustomerAccountDTO custid){
                return repository.findAll()
                        .stream()
                        .map(this::convertEntityToDTO)
                        .collect(Collectors.toList());
        }

        private CustomerAccountDTO convertEntityToDTO(AccountEntity account){
                CustomerAccountDTO customeraccountdto=new CustomerAccountDTO();
                //customeraccountdto.setCustId(account.getCust_id());
                //customeraccountdto.setAccountNumber(account.getAccountNumber());
                customeraccountdto.setBalance(account.getBalance());
                customeraccountdto.setAccType(account.getAccType());
                customeraccountdto.setStatus(account.getStatus());
                return customeraccountdto;
        }

//        public AccountEntity saveAccount(@RequestBody AccountEntity  entity){
//
//                return repository.save(entity);
//        }
}
