package com.luban.testcache.service.impl;

import com.luban.testcache.entity.NullValueResultDO;
import com.luban.testcache.entity.Order;
import com.luban.testcache.entity.R;
import com.luban.testcache.filter.RedisBloomFilter;
import com.luban.testcache.mapper.OrderMapper;
import com.luban.testcache.service.OrderService;
import com.luban.testcache.template.CacheLoadble;
import com.luban.testcache.template.CacheTemplate;
import com.luban.testcache.util.RedisLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.TimeUnit;

//@Transactional
@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    ValueOperations valueOperations;

    @Autowired
    CacheTemplate cacheTemplate;


    @Override
    public Integer insertOrder(Order order) {
        Integer integer = orderMapper.insertOrder(order);
        return integer;
    }

    @Override
    public R selectOrderById(Integer id) {
        return cacheTemplate.redisFindCache(String.valueOf(id), 10, TimeUnit.MINUTES, new CacheLoadble<Order>() {
            @Override
            public Order load() {
                return orderMapper.selectOrderById(id);
            }
        },true);
    }

    public R synchronizedSelectOrderById(Integer id) {
        return cacheTemplate.findCache(String.valueOf(id), 10, TimeUnit.MINUTES, new CacheLoadble<Order>() {
            @Override
            public Order load() {
                return orderMapper.selectOrderById(id);
            }
        });
    }


    @Override
    public List<Order> selectOrderyAll() {
        return orderMapper.selectOrderyAll();
    }
}
