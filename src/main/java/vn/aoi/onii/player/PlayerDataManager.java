package vn.aoi.onii.player;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import vn.aoi.onii.AoiMain;

import java.sql.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class PlayerManager {

    private final AoiMain plugin;
    private final Map<UUID, PlayerData> cache = new ConcurrentHashMap<>();

    public PlayerManager(AoiMain plugin) {
        this.plugin = plugin;
    }

    public void load(Player player) {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            try {
                PreparedStatement ps = plugin.getDatabase().getConnection()
                        .prepareStatement("SELECT * FROM players WHERE uuid=?");
                ps.setString(1, player.getUniqueId().toString());
                ResultSet rs = ps.executeQuery();

                PlayerData data;

                if (rs.next()) {
                    data = new PlayerData(
                            rs.getString("rank"),
                            rs.getInt("level"),
                            rs.getInt("exp")
                    );
                } else {
                    data = new PlayerData("Phàm nhân", 1, 0);
                    saveSync(player.getUniqueId(), data);
                }

                cache.put(player.getUniqueId(), data);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public void save(UUID uuid) {
        PlayerData data = cache.get(uuid);
        if (data == null) return;

        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> saveSync(uuid, data));
    }

    private void saveSync(UUID uuid, PlayerData data) {
        try {
            PreparedStatement ps = plugin.getDatabase().getConnection().prepareStatement(
                    "REPLACE INTO players(uuid,rank,level,exp) VALUES(?,?,?,?)"
            );

            ps.setString(1, uuid.toString());
            ps.setString(2, data.getRank());
            ps.setInt(3, data.getLevel());
            ps.setInt(4, data.getExp());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public PlayerData get(Player player) {
        return cache.get(player.getUniqueId());
    }

    public void shutdown() {
        cache.forEach(this::save);
    }
} 
