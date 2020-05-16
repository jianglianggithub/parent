package com;

import com.github.wxpay.sdk.WXPayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Map;

/**
 * Created by aa on 2019/11/2.
 */

@SpringBootApplication
@Controller()
public class App extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(App.class);
    }
    @Override//为了打包springboot项目
    protected SpringApplicationBuilder configure(
            SpringApplicationBuilder builder) {
        return builder.sources(this.getClass());
    }

    @RequestMapping("/list/{cp}/{ps}")
    public Object list(@PathVariable("cp") String cp, @PathVariable("ps") String ps){
        return cp+ps;
    }


    @RequestMapping("/a/b/c/d")
    public String aa(HttpServletRequest request){

        return "redirect:/f/e";
    }


    @RequestMapping("/assss")
    public void test(HttpServletResponse resp) throws IOException {

            // path是指欲下载的文件的路径。


            InputStream fis = new FileInputStream(new File("d:/sp/test.mp4"));

            resp.setContentType("application/octet-stream");
            resp.setCharacterEncoding("utf-8");
            resp.setContentLength(fis.available());
            //下载到本地的文件名，例如：abc.xlsx(注意：这里跟路径中的文件名区分开)
            String downloadName = "你要下载的文件名";
            resp.setHeader("Content-Disposition", "attachment;filename=" + downloadName+".mp4");
           resp.addHeader("Content-Length", "" + fis.available());
        byte[] buff = new byte[1024];
            BufferedInputStream bis = null;
            OutputStream os = null;
            try {
                os = resp.getOutputStream();
                bis = new BufferedInputStream(fis);
                int i = 0;
                while ((i = bis.read(buff)) != -1) {
                    os.write(buff, 0, i);
                    os.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


}


}
