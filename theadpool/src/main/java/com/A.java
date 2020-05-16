package com;


import org.apache.commons.cli.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class A {


    @RequestMapping("/abd")
    public Object aa(){
        return "1";
    }

    public static void main(String[] args) throws ParseException {

        String[] args1={"--config","aaaa"};
        main2(args1);
    }
    public static void main2(String[] args) throws ParseException {

        Option option = new Option("h",false,"~");
        option.setRequired(false);
        //第二个参数=长选项名
        Option option1 = new Option("n","config",true,"~");
        Options options = new Options();
        options.addOption(option);
        options.addOption(option1);

        CommandLineParser parser;
        CommandLine cmd = null;
        try {
             parser = new PosixParser();
             cmd = parser.parse(options, args);

        }catch (Exception e) {

        }
        System.out.println(cmd.getOptionValue("config"));
        if (cmd.hasOption("h")) {
            HelpFormatter hf = new HelpFormatter();
            hf.setWidth(110);
            hf.printHelp("testApp",options,true);//第一个参数 和第三个只是 现实的东西不同
        }
    }
}
