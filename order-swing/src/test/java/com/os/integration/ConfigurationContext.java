package com.os.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by jian zhu on 05/27/2017.
 */
@Configuration
public class ConfigurationContext {
    @Autowired
    private FooProperties properties;

    @Bean
    public FooProperties fooProperties(){
        return new FooProperties();
    }

    @Bean
    public MyService myService(){
        return new MyService(properties);
    }
}
