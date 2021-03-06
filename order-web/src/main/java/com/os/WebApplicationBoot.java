package com.os;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//@Configuration
//@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
//@ComponentScan(basePackages = {"com.os"})
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class WebApplicationBoot extends SpringBootServletInitializer{
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(WebApplicationBoot.class);
    }

    public static void main(String[] args) throws Exception{
        SpringApplication.run(WebApplicationBoot.class,args);
    }
}
