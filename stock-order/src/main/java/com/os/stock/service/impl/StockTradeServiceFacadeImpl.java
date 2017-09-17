package com.os.stock.service.impl;

import com.os.beans.entities.StockTradeItemEntity;
import com.os.stock.beans.vos.StockTradeVO;
import com.os.stock.service.StockServiceFacade;
import com.os.stock.service.StockTradeService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockTradeServiceFacadeImpl implements StockServiceFacade {
    @Autowired
    private StockTradeService stockTradeService;

    @Autowired
    private Mapper beanMapper;

    @Override
    @Transactional
    public void insert(StockTradeVO stockTradeVO) {
        StockTradeItemEntity entity = beanMapper.map(stockTradeVO, StockTradeItemEntity.class);
        stockTradeService.save(entity);
    }

    @Override
    public List<StockTradeVO> findAll() {
        return stockTradeService.queryAll().stream().map(source -> beanMapper.map(source,StockTradeVO.class)).collect(Collectors.toList());
    }
}
