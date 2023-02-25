package com.BankMS.BankMS.dTO;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
public class CustomerAccountDTO {

    private int custId;
    private int accountNumber;
    private int balance;
    private String accType;
    private String status;
}
