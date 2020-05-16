package com.config;

import com.utils.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

;import java.util.HashMap;
import java.util.Map;

// 自定义 认证提供者。
@Component
public class AuthenticationProvider implements org.springframework.security.authentication.AuthenticationProvider {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (authentication instanceof MyAuthentication){

            MyAuthentication auth=(MyAuthentication)authentication;
            String jwt = auth.getRequest().getHeader("User-Token");

            Map<String,String> userInfo=null;
            if (StringUtils.isEmpty(jwt)  || (userInfo =JwtHelper.validateLogin(jwt))==null
                || !redisTemplate.opsForHash().hasKey("user",userInfo.get("userId"))
            ){
                return null;
            }
            Authentication result= new MyAuthentication(AuthorityUtils.createAuthorityList(userInfo.get("roles").split(",")),
                    userInfo.get("userId"));
            result.setAuthenticated(true);
            return result;
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication == MyAuthentication.class;
    }
}
