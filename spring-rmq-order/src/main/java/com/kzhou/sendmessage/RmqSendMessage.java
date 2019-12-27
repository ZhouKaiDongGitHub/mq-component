package com.kzhou.sendmessage;

import com.alibaba.fastjson.JSON;
import com.kzhou.model.Order;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class RmqSendMessage {
    @Autowired
    RabbitTemplate rabbitTemplate;

    /**
     *
     * @param message 消息
     * @param routingKey 路由键
     * @param exchangeName 交换机
     */
    public void sendMessage(String exchangeName,String routingKey,String message){
        CorrelationData correlationData = new CorrelationData("订单ID:1");
        Map<String,Object> map = new HashMap<>();
        map.put("name","kzhou");
        map.put("password","11111");
        rabbitTemplate.convertAndSend(exchangeName,routingKey, JSON.toJSONString(map),correlationData);
    }

    public void order(String exchangeName,String routingKey,String message){
        CorrelationData correlationData = new CorrelationData("订单ID:00001");
        Order order = new Order("00001","ClouseOrder",666);
        rabbitTemplate.convertAndSend(exchangeName,routingKey, JSON.toJSONString(order),correlationData);
    }
}
