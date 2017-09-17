package com.os.order.repository;

import com.os.beans.entities.DishEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jian zhu on 06/02/2017.
 */
public interface DishRepository extends JpaRepository<DishEntity,Long> {
}
