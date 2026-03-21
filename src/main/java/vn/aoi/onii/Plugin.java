// File: src/main/java/vn/aoi/onii/Plugin.java
package vn.aoi.onii;

import org.bukkit.plugin.java.JavaPlugin;

public class Plugin extends JavaPlugin {

    private static Plugin instance;

    @Override
    public void onEnable() {
        instance = this;
        //【❅】 Initialize systems
        getLogger().info("Plugin Enabled!");
    }

    @Override
    public void onDisable() {
        //【❅】 Shutdown systems safely
        getLogger().info("Plugin Disabled!");
    }

    public static Plugin getInstance() {
        return instance;
    }
}
