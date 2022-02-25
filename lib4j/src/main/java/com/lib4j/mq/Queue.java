package com.lib4j.mq;

import java.time.Duration;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;


@Component
public class Queue implements IQueue {

    @Autowired
    private StringRedisTemplate redisTemplate;

    private ListOperations<String, String> listRedis;

    @PostConstruct
    public void init() {
        listRedis = redisTemplate.opsForList();
    }

    /**
     * 发送消息
     * 
     * @param name
     * @param data
     */
    public void push(String name, String data) {
        listRedis.leftPush(name, data);
    }

    public void push(String name, Object data) {
        listRedis.leftPush(name, JSON.toJSONString(data));
    }

    /**
     * 拉取消息
     * 
     * @param name
     * @return String
     */
    public String pop(String name) {
        return popAndWait(name, 0);
    }

    public String popAndWait(String name, long seconds) {
        return listRedis.rightPop(name, Duration.ofSeconds(seconds));
    }

    public Map<String, Object> popMap(String name) {
        return popMap(name, 0);
    }

    public Map<String, Object> popMap(String name, long seconds) {
        if (name == null || name == "") {
            return null;
        }
        String data = popAndWait(name, seconds);
        Map<String, Object> map = JSON.parseObject(data, Map.class);
        return map;
    }
}