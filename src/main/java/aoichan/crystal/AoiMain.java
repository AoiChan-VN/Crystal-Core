package aoichan.crystal;

import aoichan.crystal.core.*;
import aoichan.crystal.storage.*;
import aoichan.crystal.commands.GemsCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class AoiMain extends JavaPlugin {

    private static AoiMain instance;
    private StorageProvider storage;
    private GemsManager gemsManager;

    public static AoiMain get() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();
        saveResource("gems.yml", false);

        ConsoleBanner.show(this);

        setupStorage();
        this.gemsManager = new GemsManager(this);

        getCommand("gems").setExecutor(new GemsCommand());

        getServer().getPluginManager().registerEvents(new AntiDupeManager(), this);

        getLogger().info("GemsUltimate loaded successfully.");
    }

    private void setupStorage() {
        String type = getConfig().getString("storage.type");

        if ("MYSQL".equalsIgnoreCase(type)) {
            storage = new MySQLStorage(this);
        } else {
            storage = new SQLiteStorage(this);
        }

        storage.connect();
    }

    public StorageProvider getStorage() {
        return storage;
    }

    public GemsManager getGemsManager() {
        return gemsManager;
    }

    @Override
    public void onDisable() {
        if (storage != null) storage.close();
    }
}
