package com.jpush;

import java.util.HashMap;
import java.util.Map;

public class Go
{
    public static void main(String[] args) {



        //设置推送参数
        //这里可以自定义推送参数了
        Map<String, String> parm = new HashMap<String, String>();
        //设置提示信息,内容是文章标题
        parm.put("msg","收到请联系发送人");
        parm.put("title","这里是title");
        parm.put("alias","abc");
        JPushUtil.jpushAndroid(parm);

    }
}
