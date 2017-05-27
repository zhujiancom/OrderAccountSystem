package com.os.integration.testcases;

import com.os.integration.ConfigurationContext;
import com.os.integration.MyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by jian zhu on 05/27/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ConfigurationContext.class})
//@ContextConfiguration
public class FooPropertiesTest {
    @Autowired
    private MyService service;

    @Test
    public void beanTest(){
        service.init();
    }
}
