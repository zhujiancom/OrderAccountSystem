package com.os.config;

import com.os.utils.SpringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by jian zhu on 06/02/2017.
 */
@Configuration
public class SpringUtilsConfiguration {
    @Bean
    public SpringUtils loadSpringUtils(){
        return new SpringUtils();
    }
}
