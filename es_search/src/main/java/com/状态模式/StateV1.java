package com.状态模式;

public class StateV1 implements Handle {
    @Override
    public void handle(Context context) {
        System.out.println("状态 1 ");
        context.setHandle(new StateV2());
    }
}
