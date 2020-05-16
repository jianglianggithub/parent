package com.aa.aa;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class ZZQ{

    public final static ZZQ zzq=new ZZQ();

    List<MyRefence> list=new ArrayList();

    ReferenceQueue<ObjectMy> queue=new ReferenceQueue<>();

    Executor executor= Executors.newSingleThreadExecutor();

    private ZZQ () {

    }

    public WrapperBryyObject getReference(ObjectMy objectMy) {
        executor.execute(()->{
            MyRefence myRefence=null;
            while ((myRefence= (MyRefence) queue.poll()) != null ){
                    if (list.contains(myRefence)) {
                        System.out.println(myRefence.str+"   残剩了 内存泄漏");
                        list.remove(myRefence);
                    }
            }
        });
        MyRefence myRefence = new MyRefence(objectMy);
        list.add(myRefence);
        WrapperBryyObject wrapperBryyObject = new WrapperBryyObject(objectMy,myRefence);
        return wrapperBryyObject;
    }

     class MyRefence extends WeakReference<ObjectMy>  {
        String str=""; //记录 被弱引用对象 调用的方法
        public MyRefence(ObjectMy referent) {
            super(referent,ZZQ.this.queue);
        }


        public void recode(){
            str+="调用了";
        }
     }
}

