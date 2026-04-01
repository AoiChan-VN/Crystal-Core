package vn.aoi.onii.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import vn.aoi.onii.Main;
import vn.aoi.onii.data.PlayerData;
import vn.aoi.onii.manager.PlayerManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        UUID uuid = e.getPlayer().getUniqueId();

        try {
            PreparedStatement ps = Main.getInstance().getDatabase().getConnection()
                    .prepareStatement("SELECT * FROM players WHERE uuid=?");
            ps.setString(1, uuid.toString());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                PlayerData data = new PlayerData(
                        uuid,
                        rs.getString("realm"),
                        rs.getInt("level"),
                        rs.getInt("exp")
                );
                PlayerManager.set(data);
            } else {
                PlayerData data = new PlayerData(uuid, "Phàm nhân", 1, 0);
                PlayerManager.set(data);

                PreparedStatement insert = Main.getInstance().getDatabase().getConnection()
                        .prepareStatement("INSERT INTO players VALUES (?,?,?,?)");

                insert.setString(1, uuid.toString());
                insert.setString(2, "Phàm nhân");
                insert.setInt(3, 1);
                insert.setInt(4, 0);
                insert.executeUpdate();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
} 
