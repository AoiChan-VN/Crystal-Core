package vn.aoi.onii.player;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import vn.aoi.onii.Main;

import java.sql.*;
import java.util.HashMap;
import java.util.UUID;

public class PlayerDataManager implements Listener {

    private final Main plugin;
    private final HashMap<UUID, PlayerData> cache = new HashMap<>();

    public PlayerDataManager(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> loadPlayer(p));
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> savePlayer(p));
    }

    public void loadPlayer(Player player) {
        try {
            Connection conn = plugin.getDatabaseManager().getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM players WHERE uuid = ?");
            ps.setString(1, player.getUniqueId().toString());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                cache.put(player.getUniqueId(), new PlayerData(
                        rs.getString("rank"),
                        rs.getInt("level"),
                        rs.getInt("exp")
                ));
            } else {
                PlayerData data = new PlayerData("Phàm nhân", 1, 0);
                cache.put(player.getUniqueId(), data);
                savePlayer(player);
            }

            rs.close();
            ps.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void savePlayer(Player player) {
        try {
            PlayerData data = cache.get(player.getUniqueId());
            if (data == null) return;

            Connection conn = plugin.getDatabaseManager().getConnection();
            PreparedStatement ps = conn.prepareStatement(
                    "REPLACE INTO players(uuid, rank, level, exp) VALUES (?, ?, ?, ?)"
            );

            ps.setString(1, player.getUniqueId().toString());
            ps.setString(2, data.getRank());
            ps.setInt(3, data.getLevel());
            ps.setInt(4, data.getExp());

            ps.executeUpdate();
            ps.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public PlayerData getData(Player player) {
        return cache.get(player.getUniqueId());
    }
} 
