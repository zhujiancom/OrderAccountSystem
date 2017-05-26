package com.os.demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by jian zhu on 05/25/2017.
 */
public class Log4j2PropertiesConf {
    private static Logger logger = LogManager.getLogger();

    public void performSomeTask(){
        logger.debug("This is a debug message.");
        logger.info("This is an info message.");
        logger.warn("This is a warn message.");
        logger.error("This is an error message.");
        logger.fatal("This is a fatal message");
    }
}
