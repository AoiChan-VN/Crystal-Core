package aoi.aoichan.reload;

import aoi.aoichan.AoiMain;

/*
 Hệ thống reload engine
*/

public class ReloadManager {

    private final AoiMain plugin;

    public ReloadManager(AoiMain plugin) {
        this.plugin = plugin;
    }

    // 【!】Code: reload config + module
    public void reload() {

        plugin.reloadConfig();

        plugin.getModuleLoader().shutdownModules();

        plugin.getEngineLogger().info("Reload CrystalEngine hoàn tất.");

    }
} 
