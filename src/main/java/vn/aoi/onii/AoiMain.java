package vn.aoi.onii;

import org.bukkit.plugin.java.JavaPlugin;
import vn.aoi.onii.data.Database;
import vn.aoi.onii.player.PlayerManager;
import vn.aoi.onii.rank.RankManager;

public final class AoiMain extends JavaPlugin {

    private static AoiMain instance;
    private Database database;
    private PlayerManager playerManager;
    private RankManager rankManager;

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();

        this.database = new Database(this);
        database.init();

        this.rankManager = new RankManager(this);
        rankManager.load();

        this.playerManager = new PlayerManager(this);

        getLogger().info("Plugins ➝【Bật】");
    }

    @Override
    public void onDisable() {
        playerManager.shutdown();
        database.close();
    }

    public static AoiMain get() {
        return instance;
    }

    public Database getDatabase() {
        return database;
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    public RankManager getRankManager() {
        return rankManager;
    }
}
