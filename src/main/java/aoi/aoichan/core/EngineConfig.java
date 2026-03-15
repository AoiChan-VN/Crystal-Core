package aoi.aoichan.core;

import aoi.aoichan.AoiMain;
import org.bukkit.configuration.file.FileConfiguration;

/*
 * Quản lý config của CrystalEngine
 */

public class EngineConfig {

    private final AoiMain plugin;
    private final FileConfiguration config;

    public EngineConfig(AoiMain plugin) {

        this.plugin = plugin;

        // 【!】Code: Load config.yml
        this.config = plugin.getConfig();
    }

    public boolean debugEnabled() {

        // 【!】Code: đọc config debug
        return config.getBoolean("debug", false);
    }

} 
