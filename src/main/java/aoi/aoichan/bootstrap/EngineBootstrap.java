package aoi.aoichan.bootstrap;

import aoi.aoichan.core.EngineLifecycle;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Entry point của AoiEngine
 * Đây là class chính được Paper load
 */
public final class EngineBootstrap extends JavaPlugin {

    private static EngineBootstrap instance;
    private EngineLifecycle lifecycle;

    @Override
    public void onEnable() {
        instance = this;

        getLogger().info("=================================");
        getLogger().info("     AoiEngine Starting...");
        getLogger().info("=================================");

        this.lifecycle = new EngineLifecycle(this);
        this.lifecycle.start();

        getLogger().info("AoiEngine started successfully!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Shutting down AoiEngine...");

        if (lifecycle != null) {
            lifecycle.shutdown();
        }

        getLogger().info("AoiEngine stopped.");
    }

    public static EngineBootstrap getInstance() {
        return instance;
    }
} 
