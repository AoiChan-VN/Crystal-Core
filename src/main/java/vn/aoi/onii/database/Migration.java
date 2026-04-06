package vn.aoi.onii.database;

import java.sql.Connection;
import java.sql.Statement;

public class Migration {

    public static void init(DatabaseManager db) {

        try (Connection conn = db.getConnection();
             Statement st = conn.createStatement()) {

            st.executeUpdate("""
                CREATE TABLE IF NOT EXISTS cultivators (
                    uuid TEXT PRIMARY KEY,
                    realm TEXT,
                    level INTEGER,
                    exp DOUBLE
                )
            """);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
} 
