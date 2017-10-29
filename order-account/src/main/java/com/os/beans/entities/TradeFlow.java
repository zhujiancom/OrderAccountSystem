package com.os.beans.entities;

import com.os.account.constant.FlowType;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
}
