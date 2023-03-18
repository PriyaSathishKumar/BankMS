package com.BankMS.BankMS.BankEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Transactional
@Table(name="primarycustomer")
public class CustomerEntity implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int cust_Id;
    private String custName;
    private int panCard;
    private String place;
    private long mobileNo;
   @OneToMany(cascade = CascadeType.ALL)
   @JoinColumn(name="fk_cust_Id",referencedColumnName = "cust_Id")
    private List<AccountEntity> account;

}
