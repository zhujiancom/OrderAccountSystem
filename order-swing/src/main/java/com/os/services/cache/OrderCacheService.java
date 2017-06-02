package com.os.services.cache;

import com.os.modelview.OrderPage;
import com.os.order.beans.entities.OrderEntity;
import com.os.utils.DateUtil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jian zhu on 05/31/2017.
 */
@Service("OrderCacheService")
public class OrderCacheService implements InitializingBean{
    public static final String PO_OBJ_PREFIX="_PO_OBJ_";
    public static final String PAGE_OBJ_PREFIX="_PAGE_OBJ_";

    @Autowired
    private EhCacheCacheManager cachemanager;

    private Cache orderCache;

    @Override
    public void afterPropertiesSet() throws Exception {
        orderCache = cachemanager.getCache("ordersCache");
    }

    /**
     *
     * Describle(描述)：保存持久化order 到缓存
     *
     * 方法名称：putOrders
     *
     * 所在类名：OrderCacheService
     *
     * Create Time:2016年3月17日 上午10:23:43
     *
     * @param day
     * @param orders
     */
    public void putOrders(String day,List<OrderEntity> orders){
        orderCache.put(PO_OBJ_PREFIX+day, orders);
    }

    public List<OrderEntity> getOrders(String day){
        Cache.ValueWrapper wrapper = orderCache.get(PO_OBJ_PREFIX+day);
        if(wrapper == null){
            return new ArrayList<OrderEntity>();
        }
        List<OrderEntity> cachedOrders = (List<OrderEntity>) wrapper.get();
        if(CollectionUtils.isEmpty(cachedOrders)){
            return new ArrayList<OrderEntity>();
        }
        return cachedOrders;
    }

    public void removeOrders(String day){
        orderCache.evict(PO_OBJ_PREFIX+day);
    }

    /**
     *
     * Describle(描述)： 保存可视化order 到缓存
     *
     * 方法名称：putOrderPage
     *
     * 所在类名：OrderCacheService
     *
     * Create Time:2016年3月17日 上午10:25:17
     *
     * @param day
     * @param page
     */
    public void putOrderPage(String day,OrderPage page){
        orderCache.put(PAGE_OBJ_PREFIX+day, page);
    }

    public OrderPage getOrderPage(String day){
        Cache.ValueWrapper wrapper = orderCache.get(PAGE_OBJ_PREFIX+day);
        if(wrapper == null){
            try {
                return new OrderPage(DateUtil.parseDate(day, "yyyyMMdd"));
            } catch (ParseException e) {
                return new OrderPage();
            }
        }
        OrderPage orderpage = (OrderPage) wrapper.get();
        if(orderpage == null){
            try {
                return new OrderPage(DateUtil.parseDate(day, "yyyyMMdd"));
            } catch (ParseException e) {
                return new OrderPage();
            }
        }
        return orderpage;
    }

    public void removeOrderPage(String day){
        orderCache.evict(PAGE_OBJ_PREFIX+day);
    }

    public void shutdown(){
        cachemanager.getCacheManager().shutdown();
    }
}
