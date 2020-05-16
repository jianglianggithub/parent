package com.classaa;

public class A {
    private String name;

    public A(){
        System.out.println("a");
        this.name="1";
        B b = new B();
    }


   public class B{

        private String b;
        public B(){
            System.out.println("b");
            this.b="1";
            System.out.println(A.this.name);
        }
    }

    public static void main(String[] args) {
        A a = new A();

    }

}
