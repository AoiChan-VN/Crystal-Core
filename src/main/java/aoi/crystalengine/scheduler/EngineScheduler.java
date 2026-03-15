package com.aoi.crystalengine.scheduler;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

/*
#【!】Code:
Scheduler wrapper cho engine tasks
*/

public class EngineScheduler {

    private final Plugin plugin;

    public EngineScheduler(Plugin plugin) {
        this.plugin = plugin;
    }

    public void runAsync(Runnable task) {

        Bukkit.getScheduler().runTaskAsynchronously(plugin, task);
    }

    public void runSync(Runnable task) {

        Bukkit.getScheduler().runTask(plugin, task);
    }

    public void shutdown() {

    }

} 
