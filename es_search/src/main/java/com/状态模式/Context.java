package com.状态模式;

public class Context    {

    Handle handle;

    public Context(){
        handle=new StateV1();
    }
    public void handle() {
        handle.handle(this);
    }

    public void setHandle(Handle handle) {
        this.handle = handle;
    }


    public static void main(String[] args) {
        Context context = new Context();
        context.handle();
        context.handle();
    }
}
