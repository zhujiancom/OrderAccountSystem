package com.os.beans.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Created by jian zhu on 05/31/2017.
 */
@Entity
@Table(name="BUS_TB_ACCOUNT_TYPE")
public @Data
class AccountTypeEntity extends BaseEntity{
    @Column(name="ACC_TYPE_NAME")
    private String accTypeName;

    @OneToOne
    @JoinColumn(name="ACC_ID")
    private AccountEntity account;
}
