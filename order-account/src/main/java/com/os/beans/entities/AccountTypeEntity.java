package com.os.beans.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by jian zhu on 05/31/2017.
 */
@Entity
@Table(name = "BUS_TB_ACCOUNT_TYPE")
@Data
public class AccountTypeEntity extends BaseEntity {
    @Column(name = "ACC_TYPE_NAME")
    private String accTypeName;

    @OneToMany(mappedBy = "accType")
    @JsonIgnore
    private List<AccountEntity> accounts;
}
