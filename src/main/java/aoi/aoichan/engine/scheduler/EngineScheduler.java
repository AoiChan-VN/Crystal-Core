package aoi.aoichan.engine.scheduler;

import aoi.aoichan.engine.util.EngineLogger;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * EngineScheduler
 *
 * Wrapper cho Bukkit Scheduler
 * để engine control tasks.
 */
public class EngineScheduler {

    private final JavaPlugin plugin;

    public EngineScheduler(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void initialize() {
        EngineLogger.info("Scheduler ready.");
    }

    public void shutdown() {

        Bukkit.getScheduler().cancelTasks(plugin);

        EngineLogger.info("All engine tasks cancelled.");
    }

} 
