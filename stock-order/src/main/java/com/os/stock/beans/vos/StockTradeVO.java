package com.os.stock.beans.vos;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;


public @Data class StockTradeVO {
    private Long id;
    private String stockNo;
    private String stockName;
    private String tradeType;
    private Date tradeDate;
    private BigDecimal price;
    private Integer amount;
    private Integer totalAmount;
    private BigDecimal cost;
    private Integer balance;
}
