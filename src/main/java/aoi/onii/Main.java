package vn.aoi.onii;

import org.bukkit.plugin.java.JavaPlugin;
import vn.aoi.onii.command.CommandManager;
import vn.aoi.onii.config.ConfigManager;
import vn.aoi.onii.data.Database;
import vn.aoi.onii.listener.JoinListener;
import vn.aoi.onii.listener.MobKillListener;

public class Main extends JavaPlugin {

    private static Main instance;
    private Database database;

    @Override
    public void onEnable() {
        instance = this;

        ConfigManager.init(this);

        database = new Database(this);
        database.connect();

        CommandManager.init(this);

        getServer().getPluginManager().registerEvents(new JoinListener(), this);
        getServer().getPluginManager().registerEvents(new MobKillListener(), this);
    }

    @Override
    public void onDisable() {
        database.close();
    }

    public static Main getInstance() {
        return instance;
    }

    public Database getDatabase() {
        return database;
    }
}
