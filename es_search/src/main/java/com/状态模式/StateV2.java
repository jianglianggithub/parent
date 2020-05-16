package com.状态模式;

public class StateV2 implements Handle {
    @Override
    public void handle(Context handle) {
        System.out.println("状态 2");
        handle.setHandle(new StateV1());
    }
}
