package com.os.services;

import com.os.vos.HangupTabelInfoVO;
import com.os.vos.OrderItemVO;

import java.util.List;

/**
 * Created by jian zhu on 05/27/2017.
 */
public interface IMetaDataFacadeService {
    /**
     * 获取未结账的餐桌订单信息
     * @return
     */
    List<HangupTabelInfoVO> queryHangupTableInfo();

    /**
     * 查询指定日期，指定付款单号的菜品详细信息
     * @param day
     * @param payno
     * @return
     */
    List<OrderItemVO> queryItemsByPayno(String day, String payno);
}
