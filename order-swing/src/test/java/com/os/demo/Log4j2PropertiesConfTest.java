package com.os.demo;

import com.os.utils.PropertyUtils;
import org.junit.Test;

/**
 * Created by jian zhu on 05/25/2017.
 */
public class Log4j2PropertiesConfTest {
    @Test
    public void testPerformSomeTask() throws Exception{
        Log4j2PropertiesConf log4j2PropertiesConf = new Log4j2PropertiesConf();
        log4j2PropertiesConf.performSomeTask();
        System.out.println(PropertyUtils.getStringValue("application.name"));
    }

}
