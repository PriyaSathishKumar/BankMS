package com.BankMS.BankMS.BankEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="primaryaccount")
public class AccountEntity {
    @Id
    @GeneratedValue
    private int accountNumber;
    private int cust_id;
    //private String CustName;
    private int balance;
    private String accType;
    private String status;
    @ManyToOne
//    @JoinColumn(name = "cust_id")
    private CustomerEntity customer ;

}
