package com.config;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
//自定义 认证成功的  用户对象。=当前用户
public class MyAuthentication extends AbstractAuthenticationToken {

    // username
    private  Object principal;
    //密码   不存放密码 只做标识
    private Object credentials;

    private HttpServletRequest request;
    private HttpServletResponse response;

    public MyAuthentication(Collection<? extends GrantedAuthority> authorities,
                            Object principal) {
        super(authorities);
        this.principal = principal;
    }

    public MyAuthentication(HttpServletRequest request, HttpServletResponse response){
        super(null);
        this.request=request;
        this.response=response;
    }

    public void setPrincipal(Object principal) {
        this.principal = principal;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    @Override
    public Object getCredentials() {
        return credentials;
    }

    public void setCredentials(Object credentials) {
        this.credentials = credentials;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }
}
