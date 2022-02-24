package com.lib4j.rds;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPool;

@Service
public class RedisService implements IRedisService {
    @Autowired
    private JedisPool jedisPool;

    /**
     * 获取Jedis连接池资源
     * 
     * @return Jedis
     */
    public redis.clients.jedis.Jedis getInstance() {
        return jedisPool.getResource();
    }

    /**
     * 释放jedisPool资源
     * 
     * @param jedis
     */
    public void close(redis.clients.jedis.Jedis jedis) {
        if (jedis != null) {
            jedisPool.returnResource(jedis);
        }
    }

    /**
     * 实现数据写入的setInfo方法
     */
    public void set(String key, Map<String, String> value) throws Exception {
        redis.clients.jedis.Jedis jedis = getInstance();
        jedis.select(15);
        jedis.hmset(key, value);
    }

    /**
     * 实现数据查询的get方法
     */
    public Map<String, String> get(String key) throws Exception {
        redis.clients.jedis.Jedis jedis = null;
        Map<String, String> result = null;

        try {
            jedis = getInstance();
            jedis.select(15);
            // 通过key返回其所有的subkey及value
            jedis.hgetAll(key);
            result = jedis.hgetAll(key);

        } finally {
            close(jedis);
        }
        return result;
    }

}