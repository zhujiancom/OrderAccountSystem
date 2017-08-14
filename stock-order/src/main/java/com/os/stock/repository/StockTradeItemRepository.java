package com.os.stock.repository;

import com.os.stock.beans.entity.StockTradeItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface StockTradeItemRepository extends JpaRepository<StockTradeItemEntity,Long>{
}
