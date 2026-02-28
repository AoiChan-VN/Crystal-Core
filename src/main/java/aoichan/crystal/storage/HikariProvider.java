package aoidev.crystal.storage;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.File;

public class HikariProvider {

    private final HikariDataSource dataSource;

    public HikariProvider(File dataFolder, String dbFile) {

        File db = new File(dataFolder, dbFile);

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:sqlite:" + db.getAbsolutePath());
        config.setMaximumPoolSize(10);
        config.setPoolName("Gems-Hikari");
        config.setConnectionTestQuery("SELECT 1");
        config.setIdleTimeout(60000);

        this.dataSource = new HikariDataSource(config);
    }

    public HikariDataSource get() {
        return dataSource;
    }

    public void shutdown() {
        dataSource.close();
    }
}
