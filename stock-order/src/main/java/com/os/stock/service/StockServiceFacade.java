package com.os.stock.service;

import com.os.stock.beans.vos.StockTradeVO;

import java.util.List;

public interface StockServiceFacade {
    void insert(StockTradeVO stockTradeVO);

    List<StockTradeVO> findAll();
}
