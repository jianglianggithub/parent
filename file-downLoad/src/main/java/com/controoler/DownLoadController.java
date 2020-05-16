package com.controoler;


import org.springframework.cglib.core.Signature;
import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.UUID;

@Controller
public class DownLoadController {


    public static void main(String[] args) {
        final Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(ProxyTagert.class);
        Class[] callbacks=new Class[]{new M1().getClass(),new M2().getClass()};
        enhancer.setCallbackTypes(callbacks);
        final ProxyTagert o = (ProxyTagert) enhancer.create();
        //cglib 实现代理是直接 继承
        o.AAA();
    }

    @RequestMapping("/downLoadFile")
    public void down(HttpServletResponse response){
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;fileName="+ UUID.randomUUID() +".7z");

        File file = new File("D:\\aaa.7z");
        ServletOutputStream outputStream = null;
        FileInputStream fileInputStream = null;
        try {
            outputStream = response.getOutputStream();
            fileInputStream = new FileInputStream(file);
            int leng;
            byte[] bytes=new byte[1024];
            while ((leng=fileInputStream.read(bytes)) != -1){
                outputStream.write(bytes,0,leng);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (fileInputStream != null){
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }

}
