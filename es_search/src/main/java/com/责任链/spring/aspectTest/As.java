package com.责任链.spring.aspectTest;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class As {





	@Pointcut("execution(* com.责任链.spring.aspectTest..*.*(..))")
	public void pointcut(){};


	@Before("pointcut()")
	public void before(JoinPoint joinPoint){
		System.out.println("before");
	}
	@Around("pointcut()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("around 1");
		Object[] args = joinPoint.getArgs();
		Object proceed = joinPoint.proceed(args);
		System.out.println("around 2");
		return proceed;
	}


}
