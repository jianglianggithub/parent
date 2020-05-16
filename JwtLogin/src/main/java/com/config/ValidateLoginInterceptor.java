package com.config;

import com.alibaba.fastjson.JSONObject;
import com.utils.JwtHelper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;


public class ValidateLoginInterceptor extends HandlerInterceptorAdapter {



    private static Logger log = LoggerFactory.getLogger(ValidateLoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        String jwt = httpServletRequest.getHeader("User-Token");
        log.info("[登录校验拦截器]-从header中获取的jwt为:{}", jwt);
        Map<String, String> userInfo = null;
        //验证 jwt 是否正确
        if (jwt == null || (userInfo=JwtHelper.validateLogin(jwt))==null) {
            this.notLogin(httpServletResponse);
            return false;
        }

        // 是否有权限
        if (!this.validateRoles(userInfo,(HandlerMethod)handler)){
            this.notRoles(httpServletResponse);
            return false;
        }


        return true;
    }

    private boolean validateRoles(Map<String, String> userInfo,HandlerMethod handler) {
        // 从方法处理器中获取出要调用的方法
        Method method = handler.getMethod();
        // 获取出方法上的Access注解
        Access methodAccess = method.getAnnotation(Access.class);
        Access typeAccess = method.getDeclaringClass().getAnnotation(Access.class);

        if (validate(userInfo,typeAccess) && validate(userInfo, methodAccess)) {
            return true;
        }

        return false;
    }

    private boolean validate(Map<String, String> userInfo, Access access) {
        if (access == null) {
            // 如果注解为null, 说明不需要拦截, 直接放过
            return true;
        }
        if (access.roles().length > 0) {
            // 如果权限配置不为空, 则取出配置值
            String[] methodRoles = access.roles();
            String[] userRoles = userInfo.get("roles").split(",");
            for (int i = 0; i < methodRoles.length; i++) {
                for (int j = 0; j < userRoles.length; j++) {
                    if ( methodRoles[i].equals(userRoles[i]) ) {
                        return true;
                    }
                }
            }

        }
        return false;
    }


    private void notRoles(HttpServletResponse httpServletResponse) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("hmac", "");
        jsonObject.put("status", "");
        jsonObject.put("code", "4007");
        jsonObject.put("msg", "没有权限");
        jsonObject.put("data", "");
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json; charset=utf-8");
        httpServletResponse.getOutputStream().write(jsonObject.toJSONString().getBytes("UTF-8"));
    }

    private void notLogin(HttpServletResponse httpServletResponse) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("hmac", "");
        jsonObject.put("status", "");
        jsonObject.put("code", "4007");
        jsonObject.put("msg", "未登录");
        jsonObject.put("data", "");
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json; charset=utf-8");
        httpServletResponse.getOutputStream().write(jsonObject.toJSONString().getBytes("UTF-8"));
    }



}

