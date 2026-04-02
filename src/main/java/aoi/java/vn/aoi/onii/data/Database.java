package vn.aoi.onii.data;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import vn.aoi.onii.Main;

import java.sql.Connection;
import java.sql.Statement;

public class Database {

    private static HikariDataSource ds;

    public static void init(Main plugin) {
        try {
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl("jdbc:sqlite:" + plugin.getDataFolder() + "/data.db");
            config.setMaximumPoolSize(10);
            config.setPoolName("AoiChanPool");

            ds = new HikariDataSource(config);

            try (Connection conn = ds.getConnection(); Statement st = conn.createStatement()) {
                st.executeUpdate("CREATE TABLE IF NOT EXISTS players (uuid TEXT PRIMARY KEY, realm TEXT, level INT, exp DOUBLE);");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws Exception {
        return ds.getConnection();
    }

    public static void shutdown() {
        if (ds != null) ds.close();
    }
}
