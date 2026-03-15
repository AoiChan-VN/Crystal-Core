package aoi.aoichan.core;

import org.bukkit.configuration.file.FileConfiguration;
import aoi.aoichan.AoiMain;

/*
 Hệ thống config
*/

public class EngineConfig {

    private static FileConfiguration config;

    public static void load(AoiMain plugin) {

        // 【!】Code: tạo config
        plugin.saveDefaultConfig();
        config = plugin.getConfig();

    }

    public static void reload(AoiMain plugin) {

        // 【!】Code: reload config
        plugin.reloadConfig();
        config = plugin.getConfig();

    }

    public static FileConfiguration get() {
        return config;
    }

}
