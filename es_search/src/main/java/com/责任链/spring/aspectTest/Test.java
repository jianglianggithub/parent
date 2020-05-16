package com.责任链.spring.aspectTest;


import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Component
@EnableAspectJAutoProxy
public class Test {


	public Object invoker(String a){
		System.out.println(a);
		return "123";
	}
}
