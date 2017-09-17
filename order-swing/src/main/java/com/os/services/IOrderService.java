package com.os.services;

import com.os.modelview.OrderPage;
import com.os.beans.entities.OrderEntity;

/**
 * Created by jian zhu on 05/31/2017.
 */
public interface IOrderService extends IBaseService<OrderEntity, Long> {
    OrderPage queryOrderPage(String day);
}
