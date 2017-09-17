package com.os.stock.repository;

import com.os.beans.entities.StockTradeItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface StockTradeItemRepository extends JpaRepository<StockTradeItemEntity,Long>{
}
