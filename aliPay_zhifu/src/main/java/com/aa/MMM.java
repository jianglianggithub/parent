package com.aa;

import java.io.File;

public class MMM {
    public static void main(String[] args) {
        File file = new File("D:/");
        long totalSpace = file.getTotalSpace();
        System.out.println(totalSpace);
        long freeSpace = file.getFreeSpace();
        System.out.println(freeSpace);
    }
}
