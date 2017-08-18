package com.os.integration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;


/**
 * Created by jian zhu on 05/27/2017.
 */
@Service
public class MyService {
    private static final Logger logger = LogManager.getLogger();
    private final FooProperties properties;

    @Autowired
    public MyService(FooProperties properties){
        this.properties = properties;
    }

    @PostConstruct
    public void init(){
        logger.info(properties.isEnabled());
    }
}
