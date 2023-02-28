package com.BankMS.BankMS.BankEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.transaction.Transactional;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Transactional
@Table(name="primaryaccount")
public class AccountEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int accountNumber;
    private int balance;
    private String accType;
    private String status;

//    @ManyToOne
//    @JoinColumn(name = "fk_cust_id")
//    private CustomerEntity customer;

}
