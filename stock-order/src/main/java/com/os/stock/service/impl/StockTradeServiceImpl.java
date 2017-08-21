package com.os.stock.service.impl;

import com.os.stock.beans.entity.StockTradeItemEntity;
import com.os.stock.repository.StockTradeItemRepository;
import com.os.stock.service.StockTradeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StockTradeServiceImpl implements StockTradeService{
    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private StockTradeItemRepository repository;


    @Override
    public void save(StockTradeItemEntity entity) {
        repository.saveAndFlush(entity);
    }

    @Override
    public List<StockTradeItemEntity> queryAll() {
        return repository.findAll();
    }
}
