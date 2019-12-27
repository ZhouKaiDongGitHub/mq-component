package com.kzhou.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.kzhou")
public class RmqOrderConfig {

    @Bean
    public ConnectionFactory connectionFactory(){
        CachingConnectionFactory connectionFactory =  new CachingConnectionFactory("localhost",5672);
        //connectionFactory.setHost();
        //connectionFactory.setPort();
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setVirtualHost("testHost");
        //开启发送确认
        connectionFactory.setPublisherConfirms(true);
        return connectionFactory;
    }

//    @Bean
//    public DirectExchange directExchange(){
//        return new DirectExchange("directExchange");
//    }
//
//    @Bean
//    public Queue queue1(){
//        return new Queue("testQueue1",true);
//    }
//
//    @Bean
//    public Queue queue2(){
//        return new Queue("testQueue2",true);
//    }
//
//    @Bean
//    public Binding binding1(){
//        return BindingBuilder.bind(queue1()).to(directExchange()).with("direct.key1");
//    }
//
//    @Bean
//    public Binding binding2(){
//        return BindingBuilder.bind(queue2()).to(directExchange()).with("direct.key2");
//    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        //开启一个匿名内部类 :发送确认
        template.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                System.out.println(correlationData);
                System.out.println(ack);
                System.out.println(cause);
            }
        });
        //开启失败回调
        template.setMandatory(true);
        //开启一个匿名内部类：失败回调
        template.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routeKey) {
                System.out.println(message);
                System.out.println(replyCode);
                System.out.println(replyText);
                System.out.println(exchange);
                System.out.println(routeKey);
            }
        });
        return template;
    }
}
