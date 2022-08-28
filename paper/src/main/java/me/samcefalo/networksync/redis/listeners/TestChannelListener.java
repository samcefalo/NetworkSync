package me.samcefalo.networksync.redis.listeners;

import org.bukkit.Bukkit;
import redis.clients.jedis.JedisPubSub;

public class TestChannelListener extends JedisPubSub {


    public TestChannelListener() {
    }

    @Override
    public void onMessage(String channel, String message) {
        Bukkit.getConsoleSender().sendMessage("Message received. Channel: "+ channel +", Message: " + message);
    }

}
