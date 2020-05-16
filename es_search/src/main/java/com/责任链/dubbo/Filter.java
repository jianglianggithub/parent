package com.责任链.dubbo;

import java.util.Map;

public interface Filter {

    void doFilter(Invoker invoker, Map pram);
}
