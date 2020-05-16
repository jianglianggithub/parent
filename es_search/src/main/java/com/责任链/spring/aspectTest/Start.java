package com.责任链.spring.aspectTest;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Start {


	public static void main(String[] args) {
		System.out.println("1");
		AnnotationConfigApplicationContext aspectTest = new AnnotationConfigApplicationContext("com.责任链.spring.AspectTest");
		Test bean = aspectTest.getBean(Test.class);
		bean.invoker("aa");
	}
}
