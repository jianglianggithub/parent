package com.责任链.spring;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Start {


    private Object target;
    private Method targetMthod;
    private int currentindex=0;
    private Intercaptor[] intercaptors=new Intercaptor[2];

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
       Intercaptor after= new Intercaptor(){
            @Override
            public Object invoker(Start start) throws InvocationTargetException, IllegalAccessException {
                try {
                   return start.invoker();
                }finally {
                    System.out.println("after invoker");
                }
            }
        };
        Intercaptor before= new Intercaptor(){
            @Override
            public Object invoker(Start start) throws InvocationTargetException, IllegalAccessException {
                System.out.println("before invoker ok");
                return start.invoker();
            }
        };
        Start start = new Start();
        start.intercaptors[0]=before;
        start.intercaptors[1]=after;
        start.target=new Object();
        start.targetMthod=Object.class.getDeclaredMethod("toString");
        start.invoker();
    }



    public Object invoker() throws InvocationTargetException, IllegalAccessException {
        if (currentindex==intercaptors.length) {
            return targetMthod.invoke(target);
        }

        Intercaptor intercaptor = intercaptors[currentindex++];
       return intercaptor.invoker(this);
    }
}
