package com.luban.kzhou.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class TestProducter {

    public static void main(String[] args) {

        Properties properties = new Properties();
        //指定kafka服务器地址 如果是集群可以指定多个  但是就算只指定一个他也会去集群环境下寻找其他的节点地 址
        properties.setProperty("bootstrap.servers","127.0.0.1:9092");
        //key序列化器
        properties.setProperty("key.serializer", StringSerializer.class.getName());
        //value序列化器
        properties.setProperty("value.serializer",StringSerializer.class.getName());

        //kafka在某些时候可以看成是类似redis的存储系统
        KafkaProducer<String,String> kafkaProducer = new KafkaProducer<>(properties);

        ProducerRecord<String, String> stringStringProducerRecord = new ProducerRecord<>("test-topic",1,"testKey","hello");

        Future<RecordMetadata> send = kafkaProducer.send(stringStringProducerRecord);

        RecordMetadata recordMetadata = null;
        try {
            recordMetadata = send.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(recordMetadata);
    }
}
