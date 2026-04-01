package vn.aoi.onii.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import vn.aoi.onii.Main;
import vn.aoi.onii.data.PlayerData;
import vn.aoi.onii.manager.PlayerManager;

import java.sql.PreparedStatement;

public class QuitListener implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        PlayerData data = PlayerManager.get(e.getPlayer());
        if (data == null) return;

        try {
            PreparedStatement ps = Main.getInstance().getDatabase().getConnection()
                    .prepareStatement("UPDATE players SET realm=?, level=?, exp=? WHERE uuid=?");

            ps.setString(1, data.getRealm());
            ps.setInt(2, data.getLevel());
            ps.setInt(3, data.getExp());
            ps.setString(4, data.getUuid().toString());

            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
} 
