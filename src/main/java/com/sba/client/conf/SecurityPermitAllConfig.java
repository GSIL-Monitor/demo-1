package com.sba.client.conf;

import org.springframework.context.annotation.Configuration;

/**
 * @Author suosong
 * @Date 2018/9/17
 */
//@Configuration
public class SecurityPermitAllConfig /*extends WebSecurityConfigurerAdapter*/ {
    /**
     * 为了简单起见，把spring security 瘫痪掉
     * @param http
     * @throws Exception
     */
   /* @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().permitAll()
                .and().csrf().disable();
    }*/
}
