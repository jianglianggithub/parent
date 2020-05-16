package com;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.stereotype.Component;

/**
 * Created by aa on 2019/11/13.
 */
//@Component
public class BeanFactorPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        DefaultListableBeanFactory beanfactory=(DefaultListableBeanFactory)configurableListableBeanFactory;
        Thread thread = new Thread(new Runnable() {
            @Autowired
            private RabiitTest rabiitTest;

            @Override
            public void run() {
                System.out.println(rabiitTest);

            }
        });

        RootBeanDefinition rootBeanDefinition = new RootBeanDefinition();
        rootBeanDefinition.setBeanClass(thread.getClass());
        beanfactory.registerBeanDefinition("myThread",rootBeanDefinition);

    }
}
