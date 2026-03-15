package aoi.aoichan.core;

import org.bukkit.Bukkit;

/*
 Logger chuẩn engine
*/

public class EngineLogger {

    public static void log(String message) {

        // 【!】Code: log ra console
        Bukkit.getConsoleSender().sendMessage(message);

    }

}
