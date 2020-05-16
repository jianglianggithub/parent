package com.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

@Controller
@RequestMapping("/file")
public class FileUpload {

    private static final  String ROOT_PATH="D:/vv/";


    /**
     *                         type: 0,
     *                         fileName: fileName,
     *                         fileMd5: fileMd5,
     *                         fileSize: fileSize
     * @param
     * @return
     */
    @RequestMapping("/chunkInit")
    @ResponseBody
    public Object chunkInit(String fileName,String fileMd5,long fileSize){
        File file = new File(ROOT_PATH + fileMd5);
        //这个地方 可以缓存 其实  因为遍历比较耗时 缓存 上传的区间更好
        if (file.exists()){
            File[] files = file.listFiles();
            if (files.length > 0) {
                int length=0;
                for (File item : files) {
                    length+=item.length();
                }
                if (length==fileSize)
                    return "1";
                //返回chunk值 告诉前端应该继续重第几个hcunk开始上传
                else
                    return files.length;
            }
        }
        file.mkdirs(); //创建文件夹





        return "0";
    }

//                    fileName: fileName,
//                    fileMd5: fileMd5,
//                    chunk: block.chunk,
//                    fileSize: block.end - block.start
    @RequestMapping("/chunkOrver")
    @ResponseBody
    public Object chunkOrver(String fileName,String fileMd5,Integer chunk,long fileSize,MultipartFile uploadFile) throws IOException {
        File file = new File(ROOT_PATH + fileMd5);
        if (!file.exists())
            return "0";

        byte[] bytes = uploadFile.getBytes();



        //这里应该缓存一下 md5 对应的chunk。 省略。

        //----------------------




        return "aa";
    }

    @RequestMapping("/chunkUpload")
    @ResponseBody
    public Object chunkUpload(MultipartFile file,String name,long size,long chunks,long chunk) throws IOException {
        File f1 = new File(ROOT_PATH + name);

            RandomAccessFile rw = new RandomAccessFile(f1, "rw");
            rw.seek(f1.length());
            System.out.println(rw.getFilePointer()+" position ");
            rw.write(file.getBytes());
            rw.close();



        return "aa";
    }

    public static void main(String[] args) {
        File file = new File(ROOT_PATH + "aa.a");
        System.out.println(file.length());
    }
}
