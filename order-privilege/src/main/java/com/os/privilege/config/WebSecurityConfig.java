package com.os.privilege.config;

import com.os.privilege.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().requireCsrfProtectionMatcher(new AntPathRequestMatcher("**/login"))
                .and().authorizeRequests().antMatchers("/dashboard").hasRole("role_user")
                .and().formLogin().loginPage("/login").failureUrl("/login?error").defaultSuccessUrl("/dashboard")
                .and().logout().permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserService()).passwordEncoder(new Md5PasswordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/static/**")
                .and().ignoring().antMatchers("/static/**")
                .and().ignoring().antMatchers("/bootstrap/**")
                .and().ignoring().antMatchers("/fragments/**")
                .and().ignoring().antMatchers("/**/*.js")
                .and().ignoring().antMatchers("/**/*.css")
        .and().ignoring().antMatchers("/");
    }

    @Bean
    UserDetailsService customUserService(){
        return new CustomUserDetailsService();
    }
}
