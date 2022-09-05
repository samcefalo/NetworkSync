package me.samcefalo.networksync;

import me.samcefalo.networksync.api.redis.RedisExecutor;
import me.samcefalo.networksync.redis.listeners.TestChannelListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import me.samcefalo.networksync.api.redis.RedisProvider;

public final class NetworkSync extends JavaPlugin {

    private RedisProvider redisProvider;
    private final TestChannelListener testChannel = new TestChannelListener();

    @Override
    public void onEnable() {
        this.redisProvider = new RedisProvider();
        RedisExecutor redisExecutor = new RedisExecutor(this.redisProvider);

        this.redisProvider.getJedisPooled().set("Potato", "potatoooo");

        redisExecutor.subscribe("test", testChannel);

        Bukkit.getScheduler().runTaskLaterAsynchronously(this, () -> {
            redisExecutor.publish("test", "another message");
            Bukkit.getConsoleSender().sendMessage("Message send.");
        }, 100);

        long start = System.currentTimeMillis();

        this.runAsync("Potato", response -> {
            long end = System.currentTimeMillis();
            Bukkit.getConsoleSender().sendMessage("Key: Potato, Response: " + response + " took " + (end - start) + "ms..");
        });

    }

    //run in another Thread
    public void runAsync(String key, final KeyCallBack callBack) {
        Bukkit.getScheduler().runTaskAsynchronously(this, () -> {
            String response = redisProvider.getJedisPooled().get(key);

            //call the callback with the result
            if (callBack != null) callBack.onDone(response);

        });
    }

    @Override
    public void onDisable() {

    }
}
