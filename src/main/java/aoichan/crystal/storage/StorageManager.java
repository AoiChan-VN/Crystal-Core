package aoichan.crystal.storage;

import aoichan.crystal.AoiMain;
import aoichan.crystal.api.GemData;
import aoichan.crystal.core.cache.GemCache;
import org.bukkit.Bukkit;

import java.util.Set;
import java.util.UUID;

// [!] Code: Storage Manager (Bridge Between Cache and Storage)
public class StorageManager {

    private final AoiMain plugin;
    private StorageProvider provider;

    public StorageManager(AoiMain plugin) {
        this.plugin = plugin;
    }

    public void initialize() {

        String type = plugin.getConfig().getString("storage.type", "sqlite");

        if (type.equalsIgnoreCase("mysql")) {
            provider = new MySQLStorage(plugin);
        } else {
            provider = new SQLiteStorage(plugin);
        }

        provider.initialize();

        Bukkit.getConsoleSender().sendMessage("§a[Crystal Storage] Using: " + type.toUpperCase());
    }

    public GemData load(UUID uuid) {
        return provider.load(uuid);
    }

    public void save(UUID uuid) {
        provider.save(uuid);
    }

    public void saveSync(UUID uuid) {
        provider.saveSync(uuid);
    }

    // [!] Code: Batch Async Save
    public void saveBatch(Set<UUID> uuids) {
        for (UUID uuid : uuids) {
            provider.save(uuid);
        }
    }

    // [!] Code: Batch Sync Save
    public void saveBatchSync(Set<UUID> uuids) {
        for (UUID uuid : uuids) {
            provider.saveSync(uuid);
        }
    }

    public void shutdown() {
        provider.shutdown();
    }
}
