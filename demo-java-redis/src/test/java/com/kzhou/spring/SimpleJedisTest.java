package com.kzhou.spring;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

import java.util.List;

public class SimpleJedisTest {

    private static Jedis jedis;

    @BeforeClass
    public static void init(){
        jedis = new Jedis("192.168.193.128",6379);
    }
    @Test
    public void testKey(){
        System.out.println(jedis.keys("*"));
        System.out.println(jedis.exists("k1"));
        jedis.expire("k1",60);
        System.out.println(jedis.ttl("k1"));
        System.out.println(jedis.type("k1"));

        System.out.println(jedis.incr("count"));
        System.out.println(jedis.incr("count"));
        System.out.println(jedis.incr("count"));
        System.out.println(jedis.decr("count"));
        System.out.println(jedis.decr("count"));
        System.out.println(jedis.decr("count"));

        System.out.println(jedis.incrBy("count2", 2));
        System.out.println(jedis.incrBy("count2", 2));
        System.out.println(jedis.decrBy("count2", 2));
        System.out.println(jedis.decrBy("count2", 2));
    }

    @Test
    public void scanTest(){
        methodScan(0);
    }

    public static void methodScan(int curson){
        String keyMatter = "k*";
        ScanParams scanParams = new ScanParams();
        scanParams.count(2);
        scanParams.match(keyMatter);
        ScanResult<String> scanResult = jedis.scan(curson,scanParams);
        List<String> list =  scanResult.getResult();
        for (String key:list
             ) {
            System.out.println("查询出来的key: "+key);
        }
        System.out.println("=======================================");
        if(0 != scanResult.getCursor()){
            methodScan(scanResult.getCursor());
        };
    }
    @Test
    public void testString(){
        System.out.println(jedis.set("key1", "value1"));
        System.out.println(jedis.get("key1"));
        System.out.println(jedis.del("key1"));

        System.out.println(jedis.append("key1", "value1"));
        System.out.println(jedis.append("key1", "value1"));
        System.out.println(jedis.get("key1"));
        System.out.println(jedis.strlen("key1"));

        System.out.println(jedis.mset("key1", "value1", "key2", "value2"));
        System.out.println(jedis.mget("key1", "key2"));

    }
}
