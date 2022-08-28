package me.samcefalo.netoworksync.api.redis;

import redis.clients.jedis.JedisPubSub;

public class RedisExecutor {

    private RedisProvider redisProvider;

    public RedisExecutor(RedisProvider redisProvider) {
        this.redisProvider = redisProvider;
    }

    //run & block another Thread
    public void subscribe(String channel, JedisPubSub jedisPubSub) {
        new Thread(() -> {
            this.redisProvider.getJedisPooled().subscribe(jedisPubSub, "test");
        }).start();
    }

    public void publish(String channel, String message) {
        this.redisProvider.getJedisPooled().publish(channel, message);
    }
}
