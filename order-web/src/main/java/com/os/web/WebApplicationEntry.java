package com.os.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Created by jian zhu on 05/26/2017.
 */
@SpringBootApplication
public class WebApplicationEntry extends SpringBootServletInitializer{
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(WebApplicationEntry.class);
    }

    public static void main(String[] args) throws Exception{
        SpringApplication.run(WebApplicationEntry.class);
    }
}
