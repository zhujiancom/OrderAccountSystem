package com.os.services;

/**
 * Created by jian zhu on 05/27/2017.
 */
public interface IDataCleanFacadeService {
    /**
     * 删除一整天所有相关信息，可以重新从SQLSERVER DB 加载订单数据
     * 1.删除所有订单信息
     * 2.删除所有标记信息
     * 3.删除所有流水信息
     * 4.删除所有代金券统计信息
     * 5.删除饿了么刷单统计信息
     * 6.删除当日库存消费信息
     *
     * @param time
     */
    void doCleanAllOfOneDay(String time);
}
