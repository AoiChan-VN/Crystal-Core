// File: src/main/java/vn/aoi/onii/core/task/AsyncTask.java
package vn.aoi.onii.core.task;

import org.bukkit.Bukkit;
import vn.aoi.onii.Plugin;

public class AsyncTask {

    //【❅】 Run async safely (no TPS drop!)
    public static void run(Runnable runnable) {
        Bukkit.getScheduler().runTaskAsynchronously(Plugin.getInstance(), runnable);
    }
} 
