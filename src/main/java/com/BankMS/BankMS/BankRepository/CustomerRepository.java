package com.BankMS.BankMS.BankRepository;

import com.BankMS.BankMS.BankEntity.AccountEntity;
import com.BankMS.BankMS.BankEntity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity,Integer> {
//    @Query("SELECT * FROM CustomerEntity p WHERE "+"p.custName LIKE CONCAT('%',:query,'%')")+
//            " and p.customerLastName LIKE CONCAT('%',:query,'%')")
    //@Query("SELECT * FROM CustomerEntity p WHERE "+"p.custName LIKE %?query%")
    @Query("SELECT p FROM CustomerEntity p WHERE "+"p.custName LIKE CONCAT('%',:query,'%')")
    //List<CustomerEntity> findAllByCustomerNameContaining(String name);
    //List<CustomerEntity> search(String keyword);
    List<CustomerEntity> findAllBycustNameContainingAndIgnoreCase(String query);

    //String existsById(CustomerEntity id);

    //boolean existsById(CustomerEntity id);
}
