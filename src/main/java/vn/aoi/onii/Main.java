package vn.aoi.onii;

import org.bukkit.plugin.java.JavaPlugin;
import vn.aoi.onii.commands.AoiCommand;
import vn.aoi.onii.database.SQLite;
import vn.aoi.onii.listeners.ChatListener;
import vn.aoi.onii.player.PlayerManager;

public class Main extends JavaPlugin {

    private static Main instance;
    private SQLite sqlite;
    private PlayerManager playerManager;

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();

        sqlite = new SQLite(this);
        sqlite.connect();
        sqlite.createTable();

        playerManager = new PlayerManager(sqlite);

        getCommand("aoi").setExecutor(new AoiCommand(playerManager));

        getServer().getPluginManager().registerEvents(new ChatListener(playerManager), this);

        saveResource("shop.yml", false);
    }

    public static Main getInstance() {
        return instance;
    }

    public SQLite getSQLite() {
        return sqlite;
    }
}
