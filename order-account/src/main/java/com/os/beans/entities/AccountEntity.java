package com.os.beans.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * Created by jian zhu on 05/31/2017.
 */
@Entity
@Table(name="BUS_TB_ACCOUNT")
public class AccountEntity extends BaseEntity{
    /* 账户编号 */
    @Column(name="ACC_NO")
    private String accNo;

    /* 账户名称  */
    @Column(name="ACC_NAME")
    private String accName;

    /*账户类型： 现金账户，金融账户，虚拟账户，负债账户，债权账户，投资账户*/
    @OneToOne(mappedBy = "account")
    private AccountTypeEntity accType;

    /*币种*/
    @Column(name="CURRENCY")
    private String currency;

    /*流入总计*/
    @Column(name="EARNING_AMOUNT")
    private BigDecimal earningAmount;

    /*流出总计*/
    @Column(name="EXPENSE_AMOUNT")
    private BigDecimal expenseAmount;

    /* 当前余额  */
    @Column(name="BALANCE")
    private BigDecimal balance;

    @Column(name="DESCRIPTION")
    private String description;

    public String getAccNo() {
        return accNo;
    }

    public void setAccNo(String accNo) {
        this.accNo = accNo;
    }

    public String getAccName() {
        return accName;
    }

    public void setAccName(String accName) {
        this.accName = accName;
    }

    public AccountTypeEntity getAccType() {
        return accType;
    }

    public void setAccType(AccountTypeEntity accType) {
        this.accType = accType;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getEarningAmount() {
        return earningAmount;
    }

    public void setEarningAmount(BigDecimal earningAmount) {
        this.earningAmount = earningAmount;
    }

    public BigDecimal getExpenseAmount() {
        return expenseAmount;
    }

    public void setExpenseAmount(BigDecimal expenseAmount) {
        this.expenseAmount = expenseAmount;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
