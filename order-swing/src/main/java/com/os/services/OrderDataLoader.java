package com.os.services;

import com.os.swing.frames.OrderPageDisplayProvider;
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

    }
}
