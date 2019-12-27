package com.kzhou.listenmessage;

import com.alibaba.fastjson.JSON;
import com.kzhou.model.Order;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

@Component
public class RmqAcceptMessage {

    @RabbitListener(queues = "testQueue2")
    public void getOrder(Message message) throws UnsupportedEncodingException {
        String messageString = new String(message.getBody(),"UTF-8");
        System.out.println("消费者 "+ messageString);
        Order order = JSON.parseObject(messageString, Order.class);
        System.out.println(order.getOrderName()+""+order.getOrderMoney());
    }

//    @RabbitListener(queues = "testQueue1")
//    public void getMessage(String message){
//        System.out.println("消费者2 "+message);
//    }
    @RabbitListener(queues = "testQueue1")
    public void getMessage(Message message) throws UnsupportedEncodingException {
        System.out.println("消费者 "+ new String(message.getBody(),"UTF-8"));
    }
}

