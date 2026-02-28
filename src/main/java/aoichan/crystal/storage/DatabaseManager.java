package aoidev.crystal.storage;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseManager {

    private final HikariDataSource dataSource;

    public DatabaseManager(File dataFolder) {

        File dbFile = new File(dataFolder, "gems.db");

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:sqlite:" + dbFile.getAbsolutePath());
        config.setMaximumPoolSize(10);
        config.setPoolName("Gems-Hikari");
        config.setConnectionTestQuery("SELECT 1");
        config.setAutoCommit(true);

        this.dataSource = new HikariDataSource(config);
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public void shutdown() {
        dataSource.close();
    }
}
