package me.samcefalo.networksync;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import me.samcefalo.netoworksync.api.redis.RedisManager;

public final class NetworkSync extends JavaPlugin {

    private RedisManager redisManager;

    @Override
    public void onEnable() {
        this.redisManager = new RedisManager();
        //this.redisManager.set("Potato", "teste");

        long start = System.currentTimeMillis();

        this.runAsync("Potato", response -> {
            long end = System.currentTimeMillis();
            Bukkit.getConsoleSender().sendMessage("Key: Potato, Response: " + response + " took " + (end - start) + "ms..");
        });

        this.runAsync("Orange", response -> {
            long end = System.currentTimeMillis();
            Bukkit.getConsoleSender().sendMessage("Key: Orange, Response: " + response + " took " + (end - start) + "ms..");
        });

    }

    //run in another Thread
    public void runAsync(String key, final KeyCallBack callBack) {
        Bukkit.getScheduler().runTaskAsynchronously(this, () -> {
            String response = redisManager.get(key);

            // call the callback with the result
            if (callBack != null) callBack.onDone(response);

        });
    }

    @Override
    public void onDisable() {
    }
}
