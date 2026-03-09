package aoichan.crystal.infrastructure.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class ConfigLoader {

    private final JavaPlugin plugin;

    private FileConfiguration config;

    public ConfigLoader(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    // [!] Code: Load config.yml
    public void load() {

        plugin.saveDefaultConfig();
        plugin.reloadConfig();

        config = plugin.getConfig();
    }

    // [!] Code: Reload configs
    public void reload() {

        plugin.reloadConfig();
        config = plugin.getConfig();
    }

    public FileConfiguration config() {
        return config;
    }
}
