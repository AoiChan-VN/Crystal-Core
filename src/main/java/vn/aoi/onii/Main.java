package vn.aoi.onii;

import org.bukkit.plugin.java.JavaPlugin;
import vn.aoi.onii.database.DatabaseManager;
import vn.aoi.onii.player.PlayerManager;
import vn.aoi.onii.cultivation.RealmManager;
import vn.aoi.onii.commands.CultivationCommand;
import vn.aoi.onii.listeners.PlayerListener;

public class Main extends JavaPlugin {

    private static Main instance;
    private DatabaseManager databaseManager;
    private PlayerManager playerManager;
    private RealmManager realmManager;

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();

        databaseManager = new DatabaseManager(this);
        databaseManager.init();

        realmManager = new RealmManager(this);
        realmManager.loadRealms();

        playerManager = new PlayerManager(this);

        getCommand("tuvi").setExecutor(new CultivationCommand(this));

        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
    }

    @Override
    public void onDisable() {
        playerManager.saveAll();
    }

    public static Main getInstance() {
        return instance;
    }

    public DatabaseManager getDatabaseManager() {
        return databaseManager;
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    public RealmManager getRealmManager() {
        return realmManager;
    }
}
