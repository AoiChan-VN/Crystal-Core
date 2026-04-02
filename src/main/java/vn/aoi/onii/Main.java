package vn.aoi.onii;

import org.bukkit.plugin.java.JavaPlugin;
import vn.aoi.onii.command.CommandManager;
import vn.aoi.onii.config.ConfigManager;
import vn.aoi.onii.data.Database;
import vn.aoi.onii.listener.*;
import vn.aoi.onii.manager.RealmManager;
import vn.aoi.onii.task.AutoSaveTask;

public class Main extends JavaPlugin {

    private static Main instance;
    private Database db;

    public void onEnable() {
        instance = this;

        ConfigManager.init(this);
        RealmManager.load();

        db = new Database(this);
        db.connect();

        CommandManager.init(this);

        getServer().getPluginManager().registerEvents(new JoinListener(), this);
        getServer().getPluginManager().registerEvents(new QuitListener(), this);
        getServer().getPluginManager().registerEvents(new MobKillListener(), this);

        new AutoSaveTask().runTaskTimer(this, 6000, 6000);
    }

    public void onDisable() {
        db.close();
    }

    public static Main get() { return instance; }
    public Database db() { return db; }
}
