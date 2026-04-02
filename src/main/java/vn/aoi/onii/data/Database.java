package vn.aoi.onii.data;

import vn.aoi.onii.Main;
import java.sql.*;

public class Database {

    private Connection conn;

    public Database(Main p){
        try{
            conn = DriverManager.getConnection("jdbc:sqlite:"+p.getDataFolder()+"/data.db?journal_mode=WAL");
            Statement st = conn.createStatement();
            st.executeUpdate("CREATE TABLE IF NOT EXISTS players(uuid TEXT PRIMARY KEY,realm TEXT,level INT,exp INT)");
        }catch(Exception e){e.printStackTrace();}
    }

    public void connect(){}

    public Connection get(){ return conn; }

    public void close(){
        try{ if(conn!=null) conn.close(); }catch(Exception ignored){}
    }
}
