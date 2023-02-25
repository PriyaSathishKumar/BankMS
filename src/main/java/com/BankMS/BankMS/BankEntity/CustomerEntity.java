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
@Table(name="custTable")
public class CustomerEntity {
    @Id
    @GeneratedValue
    private int custId;
    private String custName;
    private int panCard;
    private String place;
    private long mobileNo;
    @OneToMany(mappedBy = "customer",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<AccountEntity> accounts;

}
