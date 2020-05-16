package com.config;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.config.auth.AuthExceptionEntryPoint;
import com.config.auth.CustomAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //添加异常
    @Resource
    private AuthExceptionEntryPoint authExceptionEntryPoint;
    @Resource
    private CustomAccessDeniedHandler accessDeniedHandler;


    //自定义认证提供者
    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Bean("authManager")
    @Override//ProviderManager 中有多个 AuthenticationProvider
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Resource
    private JwtTokenFilter jwtTokenFilter;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }

    //获取Token的接口不必拦截


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/user/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //前后端分离所以不要考虑csrf可以禁用掉
        http.csrf().disable()
                //添加异常处理
                .exceptionHandling().authenticationEntryPoint( authExceptionEntryPoint )
                .accessDeniedHandler( accessDeniedHandler )
                //
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                //拦截所有请求
                .and().authorizeRequests()
                .anyRequest().authenticated();

        //定义filter addFilterAt  用于filter替换
        http.addFilterAt(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
        //super.configure( http );
        http.formLogin().disable();
        http.httpBasic().disable();


    }



}