package vn.aoi.onii.database;

public enum DatabaseType {
    SQLITE,
    MYSQL;

    public static DatabaseType from(String s) {
        try {
            return DatabaseType.valueOf(s.toUpperCase());
        } catch (Exception e) {
            return SQLITE;
        }
    }
} 
