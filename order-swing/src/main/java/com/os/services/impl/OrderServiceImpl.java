package com.os.services.impl;

import com.os.modelview.OrderPage;
import com.os.beans.entities.OrderEntity;
import com.os.services.IOrderService;
import com.os.services.cache.OrderCacheService;
import com.os.bean.vos.OrderVO;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by jian zhu on 05/31/2017.
 */
@Component("OrderService")
public class OrderServiceImpl implements IOrderService{
    @Autowired
    private Mapper beanMapper;

    @Resource(name="OrderCacheService")
    private OrderCacheService orderCache;

    @Override
    public OrderPage queryOrderPage(String day) {
        OrderPage page = orderCache.getOrderPage(day);
        List<OrderEntity> orders = orderCache.getOrders(day);
        if(!CollectionUtils.isEmpty(orders)){
            int num = page.getOrderNum();
            if(num != orders.size()){ //缓存 orderpage 更新
                //1.更新order 数量
//                for(OrderEntity order:orders){
//                    String payno = order.getPayNo();
//                    if(page.containsOrderVO(payno)){
//                        continue;
//                    }
//                    OrderVO ordervo = beanMapper.map(order, OrderVO.class);
//                    BigDecimal totalAmount = BigDecimal.ZERO;
//                    List<OrderAccountRef> oaRefs = oaService.getOARef(order.getOrderNo());
//                    for (OrderAccountRef accountRef : oaRefs) {
//                        BigDecimal amount = accountRef.getRealAmount();
//                        AccountCode code = AccountCode.valueOf(accountRef.getAccNo());
//                        if(AccountCode.FREE_ONLINE.equals(code)){
//                            ordervo.setOnlineFreeAmount(amount);
//                            continue;
//                        }
//                        if(AccountCode.FREE_OFFLINE.equals(code)){
//                            ordervo.setFreeAmount(amount);
//                            continue;
//                        }
//                        if(amount.compareTo(BigDecimal.ZERO) >= 0){
//                            totalAmount = totalAmount.add(amount);
//                        }
//                        ordervo.setTotalAmount(totalAmount);
//                    }
//                    page.addOrderVO(ordervo);
//                }

                orders.parallelStream().filter(o -> !page.containsOrderVO(o.getPayNo())).forEach(o -> {
                    OrderVO ordervo = beanMapper.map(o, OrderVO.class);
                    BigDecimal totalAmount = BigDecimal.ZERO;
//                    List<OrderAccountRef> oaRefs = oaService.getOARef(order.getOrderNo());
                });

                //2.更新账户入账统计信息
//                try {
//                    Date queryDate = DateUtil.parseDate(day, "yyyyMMdd");
//                    List<AccountSumResult> sumRes = oaService.querySumAmount(queryDate);
//                    BigDecimal dailyAmount = BigDecimal.ZERO;
//                    for(AccountSumResult res:sumRes){
//                        AccountCode code = AccountCode.valueOf(res.getAccNo());
//                        BigDecimal amount = res.getSumAmount();
//                        if(amount.compareTo(BigDecimal.ZERO) > 0){
//                            dailyAmount = dailyAmount.add(amount);
//                        }
//                        page.addPostAccountAmount(code, amount);
//                    }
//                    page.addPostAccountAmount(AccountCode.DAILY_ALL, dailyAmount);
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
                orderCache.putOrderPage(day, page);
            }
        }
        return page;
    }
}
