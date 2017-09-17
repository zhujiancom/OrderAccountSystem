package com.os.order.repository;

import com.os.beans.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jian zhu on 06/02/2017.
 */
public interface OrderRepository extends JpaRepository<OrderEntity,Long> {
}
