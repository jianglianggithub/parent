package com;

import java.nio.ByteBuffer;

public class Baa {


    public static void main(String[] args) {
        System.out.println(ByteBuffer.allocateDirect(1024).getClass());
    }
}
