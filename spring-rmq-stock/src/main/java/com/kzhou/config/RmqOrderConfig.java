package com.kzhou.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
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

    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("directExchange");
    }

    @Bean
    public Queue queue1(){
        return new Queue("testQueue1",true);
    }

    @Bean
    public Queue queue2(){
        return new Queue("testQueue2",true);
    }

    @Bean
    public Binding binding1(){
        return BindingBuilder.bind(queue1()).to(directExchange()).with("direct.key1");
    }

    @Bean
    public Binding binding2(){
        return BindingBuilder.bind(queue2()).to(directExchange()).with("direct.key2");
    }

    @Bean
    public SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory(ConnectionFactory connectionFactory){
        SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory =
                new SimpleRabbitListenerContainerFactory();
        //这个connectionFactory就是我们自己配置的连接工厂直接注入进来
        simpleRabbitListenerContainerFactory.setConnectionFactory(connectionFactory);
        //这边设置消息确认方式由自动确认变为手动确认
        simpleRabbitListenerContainerFactory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        simpleRabbitListenerContainerFactory.setPrefetchCount(1);
        return simpleRabbitListenerContainerFactory;
    }
}
