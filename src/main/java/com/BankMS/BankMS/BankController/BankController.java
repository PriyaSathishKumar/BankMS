package com.BankMS.BankMS.BankController;

import com.BankMS.BankMS.BankEntity.AccountEntity;
import com.BankMS.BankMS.BankEntity.CustomerEntity;
import com.BankMS.BankMS.BankRepository.CustomerRepository;
import com.BankMS.BankMS.BankService.AccountService;
import com.BankMS.BankMS.BankService.CustomerService;
//import com.BankMS.BankMS.dTO.CustomerAccountDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static java.util.Optional.*;

@RestController
@RequestMapping("/bank")
public class BankController {
    @Autowired
    private AccountService accountservice;
    @Autowired
    private CustomerRepository customerrepository;
    @Autowired
    private CustomerService customerservice;
    private static final Logger logger = LogManager.getLogger(BankController.class);
    @PostMapping("/addcustomer")
    public CustomerEntity addCustomerDetails(@RequestBody CustomerEntity entity){
        logger.info("added customer Details");
        return customerservice.saveCustomerDetails(entity);
    }
    // get all Customers details
    @GetMapping("/allcustomers")
    public List<CustomerEntity> findAllCustomer() throws JsonProcessingException {

        logger.info("Get All Customer Details "+customerservice.getCustomers());
        return customerservice.getCustomers();
    }
    // get Customer details by ID
    @GetMapping("/customerById/{id}")
    public Optional<CustomerEntity> findCustomerById(@PathVariable int id) {
        logger.info("Read Customer Details By id " + id);
        Optional<CustomerEntity> customer = customerservice.getCustomerById(id);
        return customer;

    }
    // get Customer details by name

    @GetMapping("/search")
//    public List<CustomerEntity> searchCustomers(@RequestParam("name") String name) {
//        return customerrepository.findAllBycustNameContainingAndIgnoreCase(name);
//    }
    public List<CustomerEntity> getCustomerByCustomerName(@RequestParam("query") String query) {
        logger.info("Read Customer Details By Name ");
        return customerrepository.findAllBycustNameContainingAndIgnoreCase(query);
    }
    @PutMapping("/updateCustomer")
    public CustomerEntity updateAccount(@RequestBody CustomerEntity entity) {
        logger.info("Account Updated Successfully "+entity);
        return customerservice.updateCustomer(entity);
    }
    @DeleteMapping("/deleteCustomer/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable("customerId") int customerId) {
        Optional<Optional<CustomerEntity>> optionalCustomer = ofNullable(customerservice.getCustomerById(customerId));
        if (optionalCustomer.isPresent()) {
            Optional<CustomerEntity> customer = optionalCustomer.get();
            List<AccountEntity> accounts = (List<AccountEntity>) accountservice.getAccountsByCustomerId(customerId);
            if (accounts.isEmpty()) {
                customerservice.deleteCustomerById(customerId);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.badRequest().body("Cannot delete customer with active accounts");
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

//    public String deleteCustomerById(@PathVariable int id) {
//        //logger.info("Account Deleted Successfuly "+id);
//        String s = customerservice.deleteCustomerById(id);
//        //return service.deleteAccountById(id);
//        return "Customer Deleted Successfully"+s;
//    }
//    @PostMapping("/addAccount")
//    //Adding Account Details
//    public ResponseEntity<?> addAccounts(@RequestBody CustomerAccountDTO accountRequestDto) {
//        logger.info("Account Created for Customer ");
//        return accountservice.createAccount(accountRequestDto);
//    }

//    public String saveAccount(@RequestBody AccountEntity entity) {
//        logger.info("Account Added Msg ");
//        //logger.info("Priya");
//         accountservice.saveAccount(entity);
//        return "Account Created for Customer";
//    }

//    @PostMapping("/addAccount")
//    //Adding Account Details
//    public List<AccountEntity> addAccounts(@RequestBody List<AccountEntity> entities) {
//        logger.info("Multiple Account Added ");
//        return accountservice.saveAccounts(entities);
//    }
    @PostMapping("/createaccountforcustomer")
    public CustomerEntity createAccount(@RequestBody CustomerEntity customer) {
        logger.info("Account created successfully");
        return customerrepository.save(customer);
    }
    @GetMapping("/allaccounts")
    public List<AccountEntity> findAllAccount() {
        logger.info("Getting All Accounts "+accountservice.getAccounts());
        return accountservice.getAccounts();
    }
    @GetMapping("/customerallaccounts/{cust-id}")
//    public List<CustomerAccountDTO> findAllAccountDetailsforCustomer(@PathVariable int custid) {
//        logger.info("Getting All Accounts for customer");//+accountservice.getAccounts());
//        return accountservice.getAllAccountDetailsforCustomer();
//    }
      public List<AccountEntity> getAccountsByCustomerId(@PathVariable("customerId") int customerId) {
        return (List<AccountEntity>) accountservice.getAccountsByCustomerId(customerId);
    }
    @GetMapping("/accountByNumber/{accountNumber}")
    public AccountEntity findAccountById(@PathVariable int accountNumber) {
        logger.info("Get Account Details by AccountNumber "+accountNumber);
        return accountservice.getAccountById(accountNumber);
    }
//    @GetMapping("/customerallaccounts")
//    public List<CustomerAccountDTO> getAllAccountDetailsforCustomer() {
//        logger.info("Getting All Accounts "+accountservice.getAccounts());
//        return accountservice.getAllAccountDetailsforCustomer();
//    }

    //        @GetMapping("/accountByName/{customerName}")
//        public List<BankEntity> findAccountByName(@PathVariable String customerName) {
//            return service.getAccountByName(customerName);
//        }
    @PutMapping("/updateAccount")
    public AccountEntity updateAccount(@RequestBody AccountEntity entity) {
        logger.info("Account Updated Successfully "+entity);
        return accountservice.updateAccount(entity);
    }
    @DeleteMapping("/deleteAccount/{accountNumber}")
    public String deleteAccountById(@PathVariable int accountNumber) {
        logger.info("Account Deleted Successfuly "+accountNumber);
        String s = accountservice.deleteAccountById(accountNumber);
        //return service.deleteAccountById(accountNumber);
        return "Account"+accountNumber+"Deleted";
    }
}

