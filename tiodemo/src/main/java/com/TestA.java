package com;

public class TestA {


    public static void main(String[] args) {
        TestA testA = new TestA();
        testA.aaa(testA::aaa);

    }

    public void aaa(TestInterFace testInterFace){
        testInterFace.function("a");
    }

    public void aaa(String a){
        System.out.println("Aa");
    }
}
