package vn.aoi.onii.listener;

import org.bukkit.event.*;
import org.bukkit.event.player.PlayerJoinEvent;
import vn.aoi.onii.Main;
import vn.aoi.onii.data.PlayerData;
import vn.aoi.onii.manager.PlayerManager;

import java.sql.*;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        try{
            var id=e.getPlayer().getUniqueId();
            var ps=Main.get().db().get().prepareStatement("SELECT * FROM players WHERE uuid=?");
            ps.setString(1,id.toString());
            var rs=ps.executeQuery();

            if(rs.next()){
                PlayerManager.set(new PlayerData(id,rs.getString("realm"),rs.getInt("level"),rs.getInt("exp")));
            }else{
                PlayerManager.set(new PlayerData(id,"Phàm nhân",1,0));
            }
        }catch(Exception ex){ex.printStackTrace();}
    }
}
