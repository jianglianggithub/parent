package com.controller;


import com.config.Access;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLDecoder;

@RestController
@RequestMapping("/test")
@Access(roles = "a")
public class TestController {



    @RequestMapping("/go")
    @PreAuthorize("hasRole('ADMIN')")
    public Object go(){
        return "a";
    }

    @RequestMapping("/g1")
    public Object g1(){
        return "<script>\n" +
                "\tvar protocol = window.location.protocol ;\n" +
                "\tvar host = window.location.host;\n" +
                "\tvar currentDomain = protocol + \"//\" + host;\n" +
                "\t\n" +
                "\tvar encodeUrl = encodeURIComponent(\"http://www.yhd.com\");\n" +
                "\tvar errorLoginJumpUrl = currentDomain + \"/passport/login_input.do?returnUrl=\"+encodeUrl ;\n" +
                "\n" +
                "\ttry{\n" +
                "\t\twindow.opener.resetLocation(1,errorLoginJumpUrl);\n" +
                "\t\twindow.opener = null;\n" +
                "\t\twindow.open(\"\",\"_self\");\n" +
                "\t\twindow.close();\n" +
                "\t}catch(e){\n" +
                "\t\twindow.location.href=errorLoginJumpUrl;\n" +
                "\t}\n" +
                "\t\n" +
                "</script>";
    }

    public static void main(String[] args) {
        String decode = URLDecoder.decode("https%3A%2F%2Fpassport.yhd.com%2Fwechat%2Fcallback.do");
        System.out.println(decode);
    }
}
