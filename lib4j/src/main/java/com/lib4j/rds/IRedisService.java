package com.lib4j.rds;

import java.util.Map;
import redis.clients.jedis.Jedis;

public interface IRedisService {

    public redis.clients.jedis.Jedis getInstance();

    public void close(Jedis jedis);

    public void set(String key, Map<String, String> value)  throws Exception;

    public Map<String, String> get(String key)  throws Exception;

}