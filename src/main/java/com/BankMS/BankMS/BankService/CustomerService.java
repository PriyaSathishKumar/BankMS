package com.BankMS.BankMS.BankService;

import com.BankMS.BankMS.BankEntity.AccountEntity;
import com.BankMS.BankMS.BankEntity.CustomerEntity;
import com.BankMS.BankMS.BankRepository.CustomerRepository;
import com.BankMS.BankMS.dTO.CustomerAccountDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository repository;
    public CustomerEntity saveCustomerDetails(@RequestBody CustomerEntity entity){

        return repository.save(entity);
    }
    public CustomerEntity getCustomerById(int id) {

        return repository.findById(id).orElse(null);

    }
    public List<CustomerEntity> getCustomers() throws JsonProcessingException {
        return repository.findAll();
    }
    // get Customer detail by Customer name

    public List<CustomerEntity> getCustomerByCustomerName(@RequestParam("query") String query) {
        List<CustomerEntity> entity=repository.findAllByCustomerNameContaining(query);
        return entity ;
        //return null;
    }
    public CustomerEntity updateCustomer(CustomerEntity entity) {
        CustomerEntity existingCustomer = repository.findById(entity.getCust_Id()).orElse(null);
        existingCustomer.setCustName(entity.getCustName());
        existingCustomer.setPlace(entity.getPlace());
        existingCustomer.setPanCard(entity.getPanCard());
        existingCustomer.setMobileNo(entity.getMobileNo());
        return repository.save(existingCustomer);
    }
    public String  deleteCustomerById(int id) {
        repository.deleteById(id);
        return "Account- " + id + " is deleted";
    }

}
