package com.BankMS.BankMS.BankService;

import com.BankMS.BankMS.BankEntity.AccountEntity;
import com.BankMS.BankMS.BankRepository.AccountRepository;
import com.BankMS.BankMS.dTO.CustomerAccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {
        @Autowired
        private AccountRepository repository;
        public AccountEntity saveAccount(AccountEntity account) {

                return repository.save(account);
        }
        public List<AccountEntity> getAccounts(List<AccountEntity> account) {

                return repository.saveAll(account);
        }
        public List<AccountEntity> getAccounts() {

                return repository.findAll();
        }
        public AccountEntity getAccountById(int accountNumber) {

                return repository.findById(accountNumber).orElse(null);
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
                //existingAccount.setCustId(entity.getCustId());
                existingAccount.setStatus(entity.getStatus());
                return repository.save(existingAccount);
        }
        public List<CustomerAccountDTO> getAllAccountDetailsforCustomer(){
                return repository.findAll()
                        .stream()
                        .map(this::convertEntityToDTO)
                        .collect(Collectors.toList());
        }

        private CustomerAccountDTO convertEntityToDTO(AccountEntity account){
                CustomerAccountDTO customeraccountdto=new CustomerAccountDTO();
                customeraccountdto.setCustId(account.getCustomer().getCustId());
                customeraccountdto.setAccountNumber(account.getAccountNumber());
                customeraccountdto.setBalance(account.getBalance());
                customeraccountdto.setAccType(account.getAccType());
                customeraccountdto.setStatus(account.getStatus());
                return customeraccountdto;
        }

}
