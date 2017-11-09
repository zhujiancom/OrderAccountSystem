package com.os.beans.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by jian zhu on 05/31/2017.
 */
@Entity
@Table(name="BUS_TB_ACCOUNT")
@Getter @Setter
public class AccountEntity extends BaseEntity{
    /* 账户编号 */
    @Column(name="ACC_NO")
    private String accNo;

    /* 账户名称  */
    @Column(name="ACC_NAME")
    private String accName;

    /*账户类型： 现金账户，金融账户，虚拟账户，负债账户，债权账户，投资账户*/
    @ManyToOne(cascade = CascadeType.ALL,fetch= FetchType.EAGER)
    @JoinColumn(name = "ACC_TYPE_ID")
    @JsonIgnore
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

    @OneToMany(mappedBy = "account")
    private List<TradeFlow> tradeFlows;

}
