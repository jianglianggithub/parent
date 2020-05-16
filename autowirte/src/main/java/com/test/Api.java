package com.test;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Api {




    @RequestMapping(value = "/asd", method = {RequestMethod.POST, RequestMethod.GET})
    public String aa(ModelMap map){
        map.addAttribute("aaa","aaa");
        return "jsonView";
    }

}
