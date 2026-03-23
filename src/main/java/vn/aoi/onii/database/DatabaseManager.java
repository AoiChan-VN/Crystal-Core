package vn.aoi.onii.database;

import vn.aoi.onii.Main;

import java.io.File;
import java.sql.*;

public class DatabaseManager {

    private final Main plugin;
    private Connection conn;

    public DatabaseManager(Main plugin) {
        this.plugin = plugin;
    }

    public void init() {
        try {
            File file = new File(plugin.getDataFolder(), "data.db");
            plugin.getDataFolder().mkdirs();

            conn = DriverManager.getConnection("jdbc:sqlite:" + file);

            try (Statement st = conn.createStatement()) {
                st.execute("CREATE TABLE IF NOT EXISTS players(uuid TEXT PRIMARY KEY, json TEXT)");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Connection get() {
        return conn;
    }

    public void shutdown() {
        try { if (conn != null) conn.close(); } catch (Exception ignored) {}
    }
}
