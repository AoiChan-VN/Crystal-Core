package aoichan.crystal;

import aoidev.crystal.bootstrap.ConsoleBanner;
import aoidev.crystal.gem.GemManager;
import aoidev.crystal.gem.GemRegistry;
import aoidev.crystal.hook.PlaceholderHook;
import aoidev.crystal.hook.VaultHook;
import aoidev.crystal.storage.HikariProvider;
import aoidev.crystal.storage.SQLiteStorage;
import aoidev.crystal.storage.StorageAdapter;
import aoidev.crystal.ui.UISystem;
import aoidev.crystal.security.AntiDuper;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class Main extends JavaPlugin {

    private StorageAdapter storage;
    private HikariProvider hikari;
    private ExecutorService dbExecutor;

    private GemManager gemManager;
    private GemRegistry registry;
    private VaultHook vaultHook;

    @Override
    public void onEnable() {

        saveDefaultConfig();
        getDataFolder().mkdirs();

        // Dedicated DB thread pool
        dbExecutor = Executors.newFixedThreadPool(4);

        // Hikari
        hikari = new HikariProvider(
                getDataFolder(),
                getConfig().getString("storage.sqlite-file", "gems.db")
        );

        storage = new SQLiteStorage(hikari.get(), dbExecutor);

        registry = new GemRegistry();
        gemManager = new GemManager(this, storage, dbExecutor);

        // Vault Hook
        if (getServer().getPluginManager().getPlugin("Vault") != null) {
            vaultHook = new VaultHook();
            if (vaultHook.hook()) {
                getLogger().info("Vault hooked successfully.");
            }
        }

        // PlaceholderAPI Hook
        if (getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new PlaceholderHook(gemManager).register();
            getLogger().info("PlaceholderAPI hooked successfully.");
        }

        // Load gems
        gemManager.loadAll();

        ConsoleBanner.print(this);
        getLogger().info("Gems Ultimate fully initialized.");
    }

    @Override
    public void onDisable() {

        if (storage != null) storage.shutdown();
        if (hikari != null) hikari.shutdown();
        if (dbExecutor != null) dbExecutor.shutdownNow();

        getLogger().info("Gems Ultimate shutdown complete.");
    }
} 
