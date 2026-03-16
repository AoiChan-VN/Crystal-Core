package aoi.aoichan.engine.util;

import org.bukkit.Bukkit;

/**
 * EngineLogger
 *
 * Central logging system cho AoiEngine.
 */
public class EngineLogger {

    public static void info(String msg) {
        Bukkit.getLogger().info("[AoiEngine] " + msg);
    }

    public static void warn(String msg) {
        Bukkit.getLogger().warning("[AoiEngine] " + msg);
    }

    public static void error(String msg) {
        Bukkit.getLogger().severe("[AoiEngine] " + msg);
    }

} 
