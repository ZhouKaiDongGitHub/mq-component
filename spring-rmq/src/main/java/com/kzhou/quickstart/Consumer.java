package com.kzhou.quickstart;

import com.rabbitmq.client.*;

import java.io.IOException;


public class Consumer {
    public static void getMessage() throws Exception {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        //定义一个消费者，把管道传入
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                //消费者标识 消息的路由键、消息标识、消息的交换机  消息的配置  消息
                //线程 一直等待状态
                System.out.println(new String(body,"UTF-8"));
                System.out.println("消息确认");
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        };
        //开启消费
        channel.basicConsume(ConnectionUtil.QUEUE_NAME,false,defaultConsumer);

    }

    public static void main(String[] args) throws Exception {
        getMessage();
    }

}
