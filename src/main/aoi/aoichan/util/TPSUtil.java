 package aoi.aoichan.util;

import org.bukkit.Bukkit;

public class TPSUtil {

    // 【!】Code: lấy TPS server
    public static double getTPS(){

        return Bukkit.getServer().getTPS()[0];

    }

}
