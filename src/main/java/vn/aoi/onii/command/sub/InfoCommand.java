package vn.aoi.onii.command.sub;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import vn.aoi.onii.command.BaseCommand;
import vn.aoi.onii.manager.PlayerManager;

public class InfoCommand extends BaseCommand {

    public String name(){return "info";}

    public void run(CommandSender s,String[] a){
        if(!(s instanceof Player p)) return;

        var d=PlayerManager.get(p.getUniqueId());
        if(d==null) return;

        p.sendMessage("§eRealm: "+d.realm);
        p.sendMessage("§eLevel: "+d.level);
        p.sendMessage("§eEXP: "+d.exp);
    }
} 
