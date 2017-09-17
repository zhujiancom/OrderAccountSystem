package com.os.stock.service;

import com.os.beans.entities.StockTradeItemEntity;

import java.util.List;

public interface StockTradeService {
    void save(StockTradeItemEntity entity);

    List<StockTradeItemEntity> queryAll();
}
