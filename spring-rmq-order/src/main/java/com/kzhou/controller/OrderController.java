package com.kzhou.controller;

import com.kzhou.sendmessage.RmqSendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    RmqSendMessage rmqSendMessage;

    @RequestMapping("/message.do")
    public Object message(String exchangename,String key,String message){
        rmqSendMessage.sendMessage(exchangename,key,message);
        return "发送消息成功";
    }

    @RequestMapping("/order.do")
    public Object order(String exchangename,String key,String message){
        rmqSendMessage.order(exchangename,key,message);
        return "下单成功";
    }
}
