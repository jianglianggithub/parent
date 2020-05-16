package com.controller;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Encoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Created by aa on 2019/11/2.
 */

@RestController
@RequestMapping("/alipay")
public class TestController {

    @Value("${alipay.appid}")
    private String appId;

    @Value("${alipay.gateway}")
    private String gateWay;

    @Value("${alipay.privateKey}")
    private String privateKey;

    @Value("${alipay.publicKey}")
    private String publicKey;

    // pc端
    @RequestMapping("/a")
    public void aa() throws Exception {


        AlipayClient alipayClient = new
                DefaultAlipayClient(
                        gateWay,
                        appId,
                        privateKey,
                "json",
                "UTF-8",
                        publicKey,
                "RSA2" );
        AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();//创建API对应的request类

        request.setBizContent("{" +
                "    \"out_trade_no\":\"20150320010101002\"," +//商户订单号
                "    \"total_amount\":\"88.88\"," +
                "    \"subject\":\"Iphone6 16G\"," +
                "    \"store_id\":\"NJ_001\"," +
                "    \"timeout_express\":\"90m\"}");//订单允许的最晚付款时间
        AlipayTradePrecreateResponse response = alipayClient.execute(request);
        String body = response.getBody();
        System.out.println(body);
        Map map = JSON.parseObject(body, Map.class);
        Map map2 = (Map) map.get("alipay_trade_precreate_response");
        System.out.println(map2.get("qr_code"));


//根据response中的结果继续业务逻辑处理

    }

    @RequestMapping("/ss")
    @ResponseBody
    public Map aa(@RequestParam Map<String,String> map){
        System.out.println(map);
        return map;
    }

    @RequestMapping("/app")
    @ResponseBody
    public Map prePay() {
        Map result = new HashMap<>();

        //实例化客户端
        AlipayClient alipayClient = new
                DefaultAlipayClient(
                gateWay,
                appId,
                privateKey,
                "json","UTF-8",
                publicKey,
                "RSA2" );
        //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        //SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setBody("骏惠惠商");
        model.setSubject("骏惠惠商"); //商品标题
        model.setOutTradeNo("70501111111S001111129"); //商家订单编号

        model.setTimeoutExpress("30m");//超时关闭该订单时间
        model.setTotalAmount(new BigDecimal(222).toString()); //订单总金额
        model.setProductCode("QUICK_MSECURITY_PAY");

        request.setBizModel(model);
        request.setNotifyUrl("http://www.baidu.com");

        try {
            //这里和普通的接口调用不同，使用的是sdkExecute
            AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
            //就是orderString 可以直接给客户端请求，无需再做处理。

            for (String str : response.getBody().split("&")) {
                System.out.println(str);
            }
            result.put("data",response.getBody());




        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        return result;
    }

    // 手机端
    @RequestMapping("/sss")
    public String h5(HttpServletRequest httpRequest,
                   HttpServletResponse httpResponse) throws ServletException, IOException, AlipayApiException {
        AlipayClient alipayClient = new
                DefaultAlipayClient(
                gateWay,
                appId,
                privateKey,
                "json","UTF-8",
                publicKey,
                "RSA2" );
        AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();//创建API对应的request
        alipayRequest.setReturnUrl("http://127.0.0.1:8080/ss");
      //  alipayRequest.setNotifyUrl("http://127.0.0.1:8080/ss");//在公共参数中设置回跳和通知地址
        alipayRequest.setBizContent("{" +
                "    \"out_trade_no\":\"20150320010101002\"," +
                "    \"total_amount\":88.88," +
                "    \"subject\":\"Iphone6 16G\"," +
                "    \"product_code\":\"QUICK_WAP_WAY\"" +
                "  }");//填充业务参数
        String form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
        httpResponse.setContentType("text/html;charset=" + "utf-8");
        System.out.println(form);
        return form;
    }





    @RequestMapping("/aq")
    public String bytes() throws UnsupportedEncodingException {
        System.out.println("aaaaa");
        byte[] bytes=new byte[3];
        bytes[0]=97;
        bytes[1]=98;
        bytes[2]=99;

        String s = Base64.getEncoder().encodeToString(bytes);
        String abc = URLEncoder.encode("啊","utf-8");
        String decode = URLDecoder.decode(abc,"utf-8");
        System.out.println(decode);
        System.out.println(abc+"   "+s);
        return s;
    }


    public static void main(String[] args) {
        TestController testController = new TestController();
        String s = testController.d1();
        System.out.println(s);
    }


    public String d1(){
        new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        return "aaa";
    }


}
