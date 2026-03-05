package aoichan.crystal.core.manager;

import aoichan.crystal.AoiMain;
import aoichan.crystal.api.GemData;
import aoichan.crystal.core.autosave.AutoSaveManager;
import aoichan.crystal.core.cache.DirtyTracker;
import aoichan.crystal.core.cache.GemCache;
import aoichan.crystal.storage.StorageManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

// [!] Code: Gems Core Manager (Lazy Load System)
public class GemsManager {

    private final AoiMain plugin;
    private final GemCache cache;
    private final DirtyTracker dirtyTracker;
    private final StorageManager storage;

    public GemsManager(AoiMain plugin,
                       GemCache cache,
                       DirtyTracker dirtyTracker,
                       StorageManager storage) {

        this.plugin = plugin;
        this.cache = cache;
        this.dirtyTracker = dirtyTracker;
        this.storage = storage;
    }

    // [!] Code: Get Gem Data (Lazy Load)
    public GemData getData(UUID uuid) {

        if (cache.contains(uuid)) {
            return cache.get(uuid);
        }

        GemData data = storage.load(uuid);

        if (data == null) {
            data = new GemData(uuid, 0);
        }

        cache.put(uuid, data);

        return data;
    }

    // [!] Code: Get Gems
    public int getGems(Player player) {
        return getData(player.getUniqueId()).getAmount();
    }

    // [!] Code: Add Gems
    public void addGems(Player player, int amount) {

        GemData data = getData(player.getUniqueId());

        data.setAmount(data.getAmount() + amount);

        markDirty(player.getUniqueId());
    }

    // [!] Code: Remove Gems
    public void removeGems(Player player, int amount) {

        GemData data = getData(player.getUniqueId());

        int newAmount = Math.max(0, data.getAmount() - amount);

        data.setAmount(newAmount);

        markDirty(player.getUniqueId());
    }

    // [!] Code: Set Gems
    public void setGems(Player player, int amount) {

        GemData data = getData(player.getUniqueId());

        data.setAmount(amount);

        markDirty(player.getUniqueId());
    }

    // [!] Code: Mark Dirty + Schedule Save
    private void markDirty(UUID uuid) {

        dirtyTracker.markDirty(uuid);

        plugin.getAutoSaveManager().scheduleSave();
    }

    // [!] Code: Player Quit Unload
    public void unload(UUID uuid) {

        if (!cache.contains(uuid)) return;

        if (dirtyTracker.getDirtyPlayers().contains(uuid)) {
            storage.saveSync(uuid);
        }

        cache.remove(uuid);
    }
} 
