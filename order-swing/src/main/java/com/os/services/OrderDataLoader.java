package com.os.services;

import com.os.config.ApplicationContextProvider;
import com.os.swing.frames.OrderPageDisplayProvider;
import com.os.utils.DateUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;

/**
 * Created by jian zhu on 05/27/2017.
 */
public class OrderDataLoader implements Runnable {
    private static final Logger logger = LogManager.getLogger();

    private Date queryDate;

    private IOrderDataLoaderService orderLoader;

    private OrderPageDisplayProvider provider;

    public OrderDataLoader(Date queryDate,OrderPageDisplayProvider provider){
        this.queryDate = queryDate;
        this.provider = provider;
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();

        //显示 order 数据
        provider.displayOrders(() -> {
            // 加载 order 数据
            orderLoader = (IOrderDataLoaderService) ApplicationContextProvider.getBean("DBLoader");
            orderLoader.load(queryDate);

            IOrderService orderService = (IOrderService) ApplicationContextProvider.getBean("OrderService");
            String time = DateUtil.date2Str(queryDate, "yyyyMMdd");
            return orderService.queryOrderPage(time);
        });

        if(logger.isDebugEnabled()){
            long timeoffset = System.currentTimeMillis()-startTime;
            System.out.println("--- 运行时间： "+timeoffset);
        }
    }
}
