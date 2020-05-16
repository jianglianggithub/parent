package com;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by aa on 2019/11/13.
 */
//@Component
public class A  implements BeanFactoryAware{
    public BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("aaa");
        this.beanFactory=beanFactory;
    }


    public static void main(String[] args) throws IOException {
        long chunkSize=1*1024*1024; //1mb

        String path="d:/sp/";
        File file = new File(path + "chunk");
        file.mkdirs();
        File file1 = new File(path + "test.mp4");
        int chunksize= (int) (file1.length() % chunkSize ==0 ?( file1.length() / chunkSize) :( file1.length() / chunkSize +1));

        FileInputStream fileInputStream = new FileInputStream(file1);
        byte[] bytes=new byte[(int) chunkSize];
        for (int i = 0; i < chunksize; i++) {
            int length=-1;
            FileOutputStream fileOutputStream = new FileOutputStream(new File(path + "chunk/"+i));
            while ((length=fileInputStream.read(bytes)) != -1){
                    fileOutputStream.write(bytes,0,length);
            }
            fileOutputStream.close();
        }
        fileInputStream.close();


        File[] files = file.listFiles();
        List<File> files1 = Arrays.asList(files);
        Collections.sort(files1, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                return Integer.parseInt(o1.getName())-Integer.parseInt(o2.getName());
            }
        });
        FileOutputStream fileOutputStream = new FileOutputStream(new File(path + "aaa.mp4"));
        for (File file2 : files1) {
            FileInputStream fileInputStream1 = new FileInputStream(file2);
            int length=-1;
            while ((length=fileInputStream1.read(bytes)) != -1 ){
                fileOutputStream.write(bytes,0,length);
            }
            fileInputStream1.close();
        }
        fileOutputStream.close();
    }
}
