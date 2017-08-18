package com.os.modelview;

import com.os.bean.vos.OrderVO;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jian zhu on 05/27/2017.
 */
public class OrderPage implements Serializable{
    private Date orderDate;

    /**
     * Map<payno,OrderVo>
     */
    private Map<String,OrderVO> orderMapping;

    /*
    *   每日账户入账金额
    *   Map<AccountCode,Amount>
    * */
    private Map<String,BigDecimal> dailyPostAccountMap;

    public OrderPage(){
        orderMapping = new HashMap<String,OrderVO>();
        dailyPostAccountMap = new HashMap<String,BigDecimal>();
    }

    public OrderPage(Date orderDate){
        this();
        this.orderDate = orderDate;
    }

    public Map<String, OrderVO> getOrderMapping() {
        return orderMapping;
    }

    public Map<String, BigDecimal> getDailyPostAccountMap() {
        return dailyPostAccountMap;
    }

    public int getOrderNum(){
        if(CollectionUtils.isEmpty(orderMapping)){
            return 0;
        }
        return orderMapping.keySet().size();
    }

    public void clear(){
        orderMapping.clear();
        dailyPostAccountMap.clear();
    }

    public boolean containsOrderVO(String payno){
        return orderMapping.containsKey(payno);
    }

    public void addOrderVO(OrderVO order){
        orderMapping.put(order.getPayNo(), order);
    }

    public void addPostAccountAmount(String accountCode,BigDecimal amount){
        dailyPostAccountMap.put(accountCode, amount);
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public OrderVO getOrderVO(String key){
        return orderMapping.get(key);
    }
}
