package com.kzhou;

import com.kzhou.config.RmqOrderConfig;
import com.kzhou.sendmessage.RmqSendMessage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class OrderApp {
    public static void main(String[] args) {
        SpringApplication.run(OrderApp.class);
//        //模拟消息发送到服务器异常情况
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
//        context.register(RmqOrderConfig.class);
//        context.refresh();
//        //获取发送对象
//        RmqSendMessage rmqSendMessage = context.getBean(RmqSendMessage.class);
//        rmqSendMessage.sendMessage("directExchange","direct.key","testFaildRollBack");
//        //发送请求发出去之后立刻关闭掉RMQ
//        context.close();
    }
}
