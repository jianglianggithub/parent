package com;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

/**
 * Created by aa on 2019/11/13.
 */

//@Component
@DependsOn("a")
public class B implements InitializingBean {

    @Autowired
    A avc;

    public Thread thread;

    public B(){
        System.out.println("b creaer");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(avc);
        thread=new Thread(new Runnable() {
           A a;

            @Override
            public void run() {
                this.a= (A) avc.beanFactory.getBean("a");
                System.out.println(a);
            }
        });
    }
}
