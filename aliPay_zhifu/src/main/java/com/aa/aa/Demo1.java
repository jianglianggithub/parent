package com.aa.aa;

public class Demo1 {



    public static void main(String[] args) throws InterruptedException {
        aaa();
        System.gc();

        Thread.sleep(5000);
        BryyObject bryyObject1 = new BryyObject();
        WrapperBryyObject reference1 = ZZQ.zzq.getReference(bryyObject1);
        System.out.println(reference1);

    }


    private static void aaa(){

        BryyObject bryyObject = new BryyObject();
        WrapperBryyObject reference = ZZQ.zzq.getReference(bryyObject);

    }
}
