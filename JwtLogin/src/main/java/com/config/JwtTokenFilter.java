package com.config;

import com.alibaba.fastjson.JSON;
import com.sun.org.apache.xpath.internal.operations.String;
import com.utils.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//自定义Filter  替换原生 security过滤器chain 中UserNamePasswordTokenFilter
//  在原生 filter中 会调用 AuthenticationManager.authencate(0 方法认证 返回一个 Authentication  对象。相当于当前用户
// 可以自己实现 AbstartAuthentication
//  .tuthenticate() 就是 调用实现类 ProviderManager = AuthenticationManager
//ProviderManager 调用所有 AuthenticationProvider  来认证 返回 Authentication 对象   其实这些东西都可以不要
// 全部写在 这个里面 是一样的。 只是 运用了 securiy的原来的设计模式。
@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    @Autowired
    private AuthenticationManager authManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        java.lang.String token = request.getHeader("User-Token");

        //获取token，并且解析token，如果解析成功，则放入 SecurityContext
        if (token != null) {
            if ( SecurityContextHolder.getContext().getAuthentication()==null ) {
                    Authentication authenticate = authManager.authenticate(new MyAuthentication(request, response));
                    SecurityContextHolder.getContext().setAuthentication(authenticate);
            }
        }

        filterChain.doFilter(request, response);
    }



}