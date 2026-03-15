package aoi.aoichan.database;

import java.sql.Connection;
import java.sql.DriverManager;

import aoi.aoichan.AoiMain;

/*
 Database engine
*/

public class DatabaseManager {

    private final AoiMain plugin;

    private Connection connection;

    public DatabaseManager(AoiMain plugin) {
        this.plugin = plugin;
    }

    public void connect() {

        try {

            // 【!】Code: sqlite database
            connection = DriverManager.getConnection(
                    "jdbc:sqlite:" + plugin.getDataFolder() + "/engine.db"
            );

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Connection getConnection() {
        return connection;
    }

    public void disconnect() {

        try {

            if (connection != null)
                connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

} 
