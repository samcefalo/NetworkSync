package me.samcefalo.networksync.api.redis;

import lombok.Getter;
import redis.clients.jedis.JedisPooled;

@Getter
public class RedisProvider {

    private JedisPooled jedisPooled;

    public RedisProvider(){
        this.jedisPooled = new JedisPooled("localhost", 6379);
    }

}
