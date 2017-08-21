package com.os.stock.service;

import com.os.stock.beans.entity.StockTradeItemEntity;

import java.util.List;

public interface StockTradeService {
    void save(StockTradeItemEntity entity);

    List<StockTradeItemEntity> queryAll();
}
