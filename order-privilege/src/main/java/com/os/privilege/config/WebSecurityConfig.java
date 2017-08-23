package com.os.privilege.config;

import com.os.privilege.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().antMatchers("/","/login").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .defaultSuccessUrl("/chat")
//                .permitAll()
//                .and()
//                .logout()
//                .permitAll();
        http.authorizeRequests().anyRequest().authenticated()
                .and().formLogin().loginPage("/login")
                .failureUrl("/login?error")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("zj").password("zj").roles("USER")
//                .and()
//                .withUser("eric").password("eric").roles("USER")
//                .and()
//                .withUser("admin").password("admin").roles("USER");
        auth.userDetailsService(customUserService());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/static/**")
                .and().ignoring().antMatchers("/static/**")
                .and().ignoring().antMatchers("/bootstrap/**");
    }

    @Bean
    UserDetailsService customUserService(){
        return new CustomUserDetailsService();
    }
}
