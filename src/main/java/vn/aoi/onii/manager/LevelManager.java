package vn.aoi.onii.manager;

import org.bukkit.entity.Player;
import vn.aoi.onii.config.ConfigManager;
import vn.aoi.onii.data.PlayerData;
import vn.aoi.onii.task.ThunderTask;

public class LevelManager {

    public static void check(Player p, PlayerData d){

        while(true){

            String path="realms."+d.realm;

            if(ConfigManager.realms.contains(path+".exp-required")){
                int req=ConfigManager.realms.getInt(path+".exp-required");

                if(d.exp<req) return;

                d.exp-=req;
                d.realm=ConfigManager.realms.getString(path+".next-rank");
                d.level=1;
                continue;
            }

            int need=ConfigManager.realms.getInt(path+".levels."+d.level+".exp");
            if(d.exp<need) return;

            d.exp-=need;
            d.level++;

            int max=ConfigManager.realms.getInt(path+".max-level");

            if(d.level>max){
                if(ConfigManager.realms.getBoolean(path+".is-thien-kiep")){
                    new ThunderTask(p).start();
                    return;
                }
                d.realm=ConfigManager.realms.getString(path+".next-rank");
                d.level=1;
            }
        }
    }
}
