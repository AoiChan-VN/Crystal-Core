package aoichan.crystal;

import aoichan.crystal.api.GemsAPI;
import aoichan.crystal.core.*;
import aoichan.crystal.gui.GUIListener;
import aoichan.crystal.storage.*;
import aoichan.crystal.commands.GemsCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class AoiMain extends JavaPlugin {

    private static AoiMain instance;

    private StorageProvider storage;
    private GemsManager gemsManager;
    private SocketManager socketManager;
    private CacheManager cacheManager;
    private GemsAPI api;

    public static AoiMain get() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();
        saveResource("gems.yml", false);

        if (getConfig().getBoolean("banner.enabled"))
            ConsoleBanner.show(this);

        setupStorage();

        cacheManager = new CacheManager();
        gemsManager = new GemsManager(this);
        socketManager = new SocketManager(this);
        api = new GemsAPI(gemsManager, socketManager);

        getCommand("gems").setExecutor(new GemsCommand());

        getServer().getPluginManager().registerEvents(
                new AntiDupeManager(socketManager), this);
        getServer().getPluginManager().registerEvents(
                new GUIListener(), this);

        getLogger().info("GemsUltimate Production loaded.");
    }

    private void setupStorage() {
        String type = getConfig().getString("storage.type");

        if ("MYSQL".equalsIgnoreCase(type))
            storage = new MySQLStorage(this);
        else
            storage = new SQLiteStorage(this);

        storage.connect();
    }

    public GemsAPI getAPI() {
        return api;
    }

    public StorageProvider getStorage() {
        return storage;
    }

    @Override
    public void onDisable() {
        if (storage != null) storage.close();
    }
}
