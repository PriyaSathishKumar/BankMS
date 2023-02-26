package com.BankMS.BankMS.BankRepository;

import com.BankMS.BankMS.BankEntity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface AccountRepository extends JpaRepository<AccountEntity,Integer>{
    //List<AccountEntity> createAccount(AccountEntity account);
    List<AccountEntity> findAccountsByCustomerId(int customerId);
    List<AccountEntity> findByCustomerId(int customerId);
}
