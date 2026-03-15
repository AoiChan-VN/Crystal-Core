package aoi.aoichan.server;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

import aoi.aoichan.AoiMain;

/*
 Theo dõi TPS
*/

public class TPSMonitor {

    private final AoiMain plugin;

    private BukkitTask task;

    private double tps;

    public TPSMonitor(AoiMain plugin) {
        this.plugin = plugin;
    }

    public void start() {

        task = Bukkit.getScheduler().runTaskTimer(plugin, () -> {

            tps = Bukkit.getTPS()[0];

        }, 20, 20);

    }

    public double getTPS() {
        return tps;
    }

    public void stop() {

        if (task != null)
            task.cancel();

    }

} 
