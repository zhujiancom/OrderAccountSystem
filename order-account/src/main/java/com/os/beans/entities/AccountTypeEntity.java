package com.os.beans.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Created by jian zhu on 05/31/2017.
 */
@Entity
@Table(name="BUS_TB_ACCOUNT_TYPE")
public class AccountTypeEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY) // MYSQL ID generator
    @Column(name="ID", nullable=false,updatable=false)
    private long id;

    @Column(name="ACC_TYPE_NAME")
    private String accTypeName;

    @OneToOne
    @JoinColumn(name="ACC_ID")
    private AccountEntity account;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAccTypeName() {
        return accTypeName;
    }

    public void setAccTypeName(String accTypeName) {
        this.accTypeName = accTypeName;
    }

    public AccountEntity getAccount() {
        return account;
    }

    public void setAccount(AccountEntity account) {
        this.account = account;
    }
}
