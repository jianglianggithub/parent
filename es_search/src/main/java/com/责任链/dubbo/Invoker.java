package com.责任链.dubbo;

import java.util.Map;

public interface Invoker {
    void invoke(Map param);
}
