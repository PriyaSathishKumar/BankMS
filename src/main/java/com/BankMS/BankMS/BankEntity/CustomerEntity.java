package com.BankMS.BankMS.BankEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="primarycustomer")
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int cust_Id;
    private String custName;
    private int panCard;
    private String place;
    private long mobileNo;
   @OneToMany(mappedBy = "customer",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<AccountEntity> accounts;

//    public boolean isPresent() {
////        int id;
////        this.cust_Id=id;
//        return true;
//    }
}
