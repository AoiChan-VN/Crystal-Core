package vn.aoi.onii.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import vn.aoi.onii.Main;

import java.io.File;

public class MessageManager {

    private static FileConfiguration config;

    public static void init(Main plugin) {
        File file = new File(plugin.getDataFolder(), "messages.yml");

        if (!file.exists()) {
            plugin.saveResource("messages.yml", false);
        }

        config = YamlConfiguration.loadConfiguration(file);
    }

    public static String get(String path) {
        return config.getString("messages." + path, "Missing: " + path)
                .replace("&", "§");
    }
}
