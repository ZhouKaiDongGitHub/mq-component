package com.kzhou.quickstart;

import com.rabbitmq.client.*;

import java.io.IOException;


public class Producer {

    public static void sendByExchange(String message) throws Exception {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        //声明队列
        //队列名称   队列是否需要持久化   队列是否单一队列  是否自动删除  对队列的配置map(容纳最大消息，寿命等等)
        //channel.queueDeclare(ConnectionUtil.QUEUE_NAME,true,false,false,null);
        //声明交换机  消息到队列中必须经过交换机  如果没有声明交换机RMQ有默认的交换机
        //channel.exchangeDeclare(ConnectionUtil.EXCHANGE_NAME,"fanout");
        //交换机和队列进行绑定
        //channel.queueBind(ConnectionUtil.EXCHANGE_NAME,ConnectionUtil.QUEUE_NAME,"");
        //发布消息到交换机上面，进行队列匹配
        //交换机名称 路由键名称（默认的队列名称）队列消息配置  消息
        channel.basicPublish("",ConnectionUtil.QUEUE_NAME,null,message.getBytes());
        System.out.println("发送的消息为："+ message);
        channel.close();
        connection.close();
    }

    public static void main(String[] args) throws Exception {
        sendByExchange("helloWorld12345");
    }
}
