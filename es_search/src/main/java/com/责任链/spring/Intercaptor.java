package com.责任链.spring;

import java.lang.reflect.InvocationTargetException;

public interface Intercaptor {





    Object invoker(Start start) throws InvocationTargetException, IllegalAccessException;

}
