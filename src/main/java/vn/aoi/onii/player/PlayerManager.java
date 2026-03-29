package vn.aoi.onii.player;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import vn.aoi.onii.database.Database;
import vn.aoi.onii.Main;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class PlayerManager {

    private final Database database;
    private final Map<UUID, PlayerData> cache = new ConcurrentHashMap<>();

    public PlayerManager(Database database) {
        this.database = database;
    }

    // LOAD DATA ASYNC
    public void load(Player player) {
        Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(), () -> {
            PlayerData data = database.load(player);
            cache.put(player.getUniqueId(), data);
        });
    }

    // GET
    public PlayerData get(Player player) {
        return cache.get(player.getUniqueId());
    }

    // SAVE ONE
    public void save(Player player) {
        PlayerData data = cache.get(player.getUniqueId());
        if (data == null) return;

        Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(), () -> {
            database.save(player, data);
        });
    }

    // SAVE ALL
    public void saveAll() {
        cache.forEach((uuid, data) -> {
            Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(), () -> {
                database.save(uuid, data);
            });
        });
    }

    // UNLOAD
    public void unload(Player player) {
        save(player);
        cache.remove(player.getUniqueId());
    }
}
