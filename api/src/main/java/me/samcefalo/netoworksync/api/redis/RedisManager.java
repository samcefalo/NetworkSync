package me.samcefalo.netoworksync.api.redis;

import redis.clients.jedis.JedisPooled;

public class RedisManager {

    private JedisPooled jedisPooled;

    public RedisManager(){
        this.jedisPooled = new JedisPooled("localhost", 6379);
    }

    public void set(String key, String value){
        this.jedisPooled.set(key, value);
    }

    public String get(String key){
        return this.jedisPooled.get(key);
    }

    public void delete(String key){
        this.jedisPooled.del(key);
    }

    public void close(){
        this.jedisPooled.close();
    }

}
