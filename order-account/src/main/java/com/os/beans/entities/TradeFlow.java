package com.os.beans.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.os.account.constant.FlowType;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name="BUS_TRADE_FLOW")
public class TradeFlow extends BaseEntity {
    @Column
    private Date tradeDate;

    @Column
    @Enumerated(EnumType.STRING)
    private FlowType flowType;

    @Column
    private BigDecimal amount;

    @Column
    private String remark;

    @ManyToOne(cascade = CascadeType.ALL,fetch= FetchType.EAGER)
    @JoinColumn(name = "ACCOUNT_ID")
    @JsonIgnore
    private AccountEntity account;
}
