package com.os.stock.beans.vos;

import lombok.Data;

@Data
public class StockTradeVO {
    private Long id;
    private String stockNo;
    private String stockName;

    public StockTradeVO(){
        super();
    }
}
