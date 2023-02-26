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
    @Query(value="SELECT * FROM CustomerEntity p WHERE "+"p.custName LIKE CONCAT('%',:query,'%')",nativeQuery = true)
    List<CustomerEntity> findAllByCustomerNameContaining(String query);
    //List<CustomerEntity> findByNameContainingIgnoreCase(String query);

    boolean existsById(CustomerEntity id);
}
