package vn.aoi.onii.config;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import vn.aoi.onii.AoiPlugin;

import java.io.File;

public class ConfigManager {

    private final AoiPlugin plugin;

    private FileConfiguration config;
    private FileConfiguration messages;
    private FileConfiguration realms;
    private FileConfiguration mobs;

    public ConfigManager(AoiPlugin plugin) {
        this.plugin = plugin;
        loadAll();
    }

    public void loadAll() {

        plugin.saveDefaultConfig();
        config = plugin.getConfig();

        messages = loadFile("messages.yml");
        realms = loadFile("realms.yml");
        mobs = loadFile("mobs.yml");
    }

    private FileConfiguration loadFile(String name) {

        File file = new File(plugin.getDataFolder(), name);

        if (!file.exists()) {
            plugin.saveResource(name, false);
        }

        return YamlConfiguration.loadConfiguration(file);
    }

    // ================= MESSAGE =================

    public String msg(String path, String... placeholders) {

        String msg = messages.getString(path, "&cMissing: " + path);

        for (int i = 0; i < placeholders.length; i += 2) {
            msg = msg.replace(placeholders[i], placeholders[i + 1]);
        }

        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    // ================= REALMS =================

    public FileConfiguration getRealms() {
        return realms;
    }

    // ================= MOBS =================

    public FileConfiguration getMobs() {
        return mobs;
    }

    public double getMobExp(String mob) {
        return mobs.getDouble("mobs." + mob + ".exp", 0);
    }

    // ================= CONFIG =================

    public FileConfiguration getConfig() {
        return config;
    }
}
