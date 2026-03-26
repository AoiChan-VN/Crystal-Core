package vn.aoi.onii.player;

import vn.aoi.onii.database.Database;
import vn.aoi.onii.enums.Realm;
import vn.aoi.onii.enums.Stage;

import java.sql.*;
import java.util.HashMap;
import java.util.UUID;

public class PlayerManager {

    private final Database db;
    private final HashMap<UUID, PlayerData> cache = new HashMap<>();

    public PlayerManager(Database db) {
        this.db = db;
    }

    public PlayerData get(UUID uuid, String name) {
        if (cache.containsKey(uuid)) return cache.get(uuid);

        try {
            PreparedStatement ps = db.getConnection().prepareStatement(
                    "SELECT * FROM players WHERE uuid=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                PlayerData data = new PlayerData(uuid, name);
                data.setRealm(Realm.valueOf(rs.getString("realm")));
                data.setStage(Stage.valueOf(rs.getString("stage")));
                data.setSect(rs.getString("sect"));

                cache.put(uuid, data);
                return data;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        PlayerData data = new PlayerData(uuid, name);
        save(data);
        cache.put(uuid, data);
        return data;
    }

    public void save(PlayerData data) {
        try {
            PreparedStatement ps = db.getConnection().prepareStatement("""
                INSERT OR REPLACE INTO players(uuid,name,realm,stage,sect)
                VALUES(?,?,?,?,?)
            """);

            ps.setString(1, data.getUuid().toString());
            ps.setString(2, data.getName());
            ps.setString(3, data.getRealm().name());
            ps.setString(4, data.getStage().name());
            ps.setString(5, data.getSect());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
