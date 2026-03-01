package aoichan.crystal.storage;

import java.sql.Connection;
import java.sql.Statement;

public class MySQLStorage implements StorageProvider {

    private final DatabasePool pool;

    public MySQLStorage(DatabasePool pool) {
        this.pool = pool;
    }

    @Override
    public void initTables() {
        try (Connection conn = pool.getDataSource().getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS player_gems (" +
                            "uuid VARCHAR(36) PRIMARY KEY, " +
                            "data TEXT)"
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        // Hikari managed by pool.shutdown()
    }
}
