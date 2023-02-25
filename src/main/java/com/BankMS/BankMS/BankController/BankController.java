package com.BankMS.BankMS.BankController;

import com.BankMS.BankMS.BankEntity.AccountEntity;
import com.BankMS.BankMS.BankEntity.CustomerEntity;
import com.BankMS.BankMS.BankService.AccountService;
import com.BankMS.BankMS.BankService.CustomerService;
import com.BankMS.BankMS.dTO.CustomerAccountDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bank")
public class BankController {
    @Autowired
    private AccountService accountservice;
    @Autowired
    private CustomerService customerservice;
    @PostMapping("/addcustomer")
    public CustomerEntity addCustomerDetails(@RequestBody CustomerEntity entity){
        //logger.info("added customer Details");
        return customerservice.saveCustomerDetails(entity);
    }
    // get all Customers details
    @GetMapping("/allcustomers")
    public List<CustomerEntity> findAllCustomer() throws JsonProcessingException {

        //logger.info("Get All Customer Details "+service.getCustomers());
        return customerservice.getCustomers();
    }
    // get Customer details by ID
    @GetMapping("/customerById/{id}")
    public CustomerEntity findCustomerById(@PathVariable int id) {
        //logger.info("Read Customer Details By id " + id);
        CustomerEntity customer=customerservice.getCustomerById(id);
        try{
            if(customer==null){
                throw new Exception("Customer with ID " + id + " not found");
                //return null;
            }else{
                return customerservice.getCustomerById(id);
            }
        }catch (Exception e){
            return null;
        }finally {
            //logger.info("Exited");
            return customerservice.getCustomerById(id);
        }

    }
    // get Customer details by name
    @GetMapping("/search")
    public List<CustomerEntity> getCustomerByCustomerName(@RequestParam("query") String query) {
        //logger.info("Read Customer Details By Name ");
        return customerservice.getCustomerByCustomerName(query);
    }
    @PutMapping("/updateCustomer")
    public CustomerEntity updateAccount(@RequestBody CustomerEntity entity) {
        //logger.info("Account Updated Successfully "+entity);
        return customerservice.updateCustomer(entity);
    }
    @DeleteMapping("/deleteCustomer/{id}")
    public String deleteCustomerById(@PathVariable int id) {
        //logger.info("Account Deleted Successfuly "+id);
        String s = customerservice.deleteCustomerById(id);
        //return service.deleteAccountById(id);
        return "Customer Deleted Successfully"+s;
    }
    //@PostMapping("/addAccount")
    //Adding New Account Details
//    public BankEntity addAccount(@RequestBody BankEntity entity) {
//        logger.info("Account Added Msg ");
//        logger.info("Priya");
//        return service.saveAccount(entity);
//    }
//    @ResponseStatus(HttpStatus.CREATED)
//    public AccountEntity createAccountForCustomer(@PathVariable int custId) {
//        //AccountEntity entity = accountservice.getAccountById(accountNumber);
//        try{
//        if (entity== null) {
//            throw new Exception("Account Number" + accountNumber + " not found");
//        }catch(Exception e){
//            return
//            }
//        BankEntity entity = service.createAccountForCustomer(customer);
//        return entity;
//    }

    @PostMapping("/addAccount")
    //Adding Account Details
    public List<AccountEntity> addAccounts(@RequestBody List<AccountEntity> entities) {
        //logger.info("Multiple Account Added ");
        return accountservice.getAccounts(entities);
    }
    @GetMapping("/allaccounts")
    public List<AccountEntity> findAllAccount() {
        //logger.info("Getting All Accounts "+service.getAccounts());
        return accountservice.getAccounts();
    }
    @GetMapping("/accountByNumber/{accountNumber}")
    public AccountEntity findAccountById(@PathVariable int accountNumber) {
        //logger.info("Get Account Details by AccountNumber "+accountNumber);
        return accountservice.getAccountById(accountNumber);
    }
    @GetMapping("/customerallaccounts")
    public List<CustomerAccountDTO> getAllAccountDetailsforCustomer() {
        //logger.info("Getting All Accounts "+service.getAccounts());
        return accountservice.getAllAccountDetailsforCustomer();
    }

    //        @GetMapping("/accountByName/{customerName}")
//        public List<BankEntity> findAccountByName(@PathVariable String customerName) {
//            return service.getAccountByName(customerName);
//        }
    @PutMapping("/updateAccount")
    public AccountEntity updateAccount(@RequestBody AccountEntity entity) {
        //logger.info("Account Updated Successfully "+entity);
        return accountservice.updateAccount(entity);
    }
    @DeleteMapping("/deleteAccount/{accountNumber}")
    public String deleteAccountById(@PathVariable int accountNumber) {
        //logger.info("Account Deleted Successfuly "+accountNumber);
        String s = accountservice.deleteAccountById(accountNumber);
        //return service.deleteAccountById(accountNumber);
        return "Account"+accountNumber+"Deleted";
    }
}
