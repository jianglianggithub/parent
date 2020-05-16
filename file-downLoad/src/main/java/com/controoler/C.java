package com.controoler;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.RegexPatternTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Enumeration;
import java.util.Set;

@Service
public class C extends ClassPathScanningCandidateComponentProvider  {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, IOException {

//        Class<?> aClass = Class.forName("org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider");
//
//        Constructor<?> constructor = aClass.getDeclaredConstructor();
//        constructor.setAccessible(true);
//        ClassPathScanningCandidateComponentProvider o = (ClassPathScanningCandidateComponentProvider) constructor.newInstance();
//        o.addIncludeFilter(new AnnotationTypeFilter(Controller.class));
//        o.findCandidateComponents("com.controoler");

      //classpath*:com/controoler/**/*.class
        final PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();
        final Resource[] resources = pathMatchingResourcePatternResolver.getResources("classpath*:com"+File.separator+"controoler"+File.separator+"**"+File.separator+"*.*");
        for (Resource resource : resources) {

        }
        MetadataReader metadataReader = new CachingMetadataReaderFactory().getMetadataReader("com.controoler.C");
        System.out.println();
    }



}
