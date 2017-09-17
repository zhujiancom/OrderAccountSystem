package com.os.beans.entities;

import com.os.stock.contants.TradeType;
import lombok.Data;
import lombok.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="STOCK_TRADE_ITEM")
@Data
public class StockTradeItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, updatable = false)
    private Long id;

    @Column(name="STOCK_NO")
    private String stockNo;

    @Column(name="STOCK_NAME")
    private String stockName;

    @Enumerated(EnumType.STRING)
    @Column(name="TRADE_TYPE")
    private TradeType tradeType;

    @Column(name="TRADE_DATE")
    private Date tradeDate;

    @Column(name="TURNOVER")
    private BigDecimal turnover;

    @Column(name="TRADE_PRICE")
    private BigDecimal tradePrice;

    @Column(name="TURNOVER_SUM")
    private BigDecimal turnoverSum;

    @Column(name="CURRENT_COST")
    private BigDecimal currentCost;

    @Column(name="RESIDUE")
    private BigDecimal residue;
}
