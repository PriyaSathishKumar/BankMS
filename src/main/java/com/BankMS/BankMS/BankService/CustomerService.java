package com.BankMS.BankMS.BankService;

import com.BankMS.BankMS.BankEntity.CustomerEntity;
import com.BankMS.BankMS.BankRepository.CustomerRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository repository;
    public CustomerEntity saveCustomerDetails(@RequestBody CustomerEntity entity){

        return repository.save(entity);
    }
    public Optional<CustomerEntity> getCustomerById(int id) {
        Optional<CustomerEntity> customer=repository.findById(id);
        try{
            if(customer==null){
                //throw new Exception("Customer with ID " + id + " not found");
                return null;
            }else{
                return repository.findById(id);
            }
        }catch (Exception e){
            return null;
        }finally {
            //logger.info("Customer-ID Not valid-Exited");
            return repository.findById(id);
        }
     }
    public List<CustomerEntity> getCustomers() throws JsonProcessingException {
        return repository.findAll();
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
        return "Customer- " + id + " is deleted";
    }

}
