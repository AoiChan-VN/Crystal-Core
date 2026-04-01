package vn.aoi.onii.command;

import org.bukkit.command.*;
import org.bukkit.entity.Player;
import vn.aoi.onii.AoiMain;

import java.lang.reflect.Method;

public class CommandManager {

    private final AoiMain plugin;

    public CommandManager(AoiMain plugin){this.plugin=plugin;}

    public void registerCommands(){
        plugin.getCommand("tuvi").setExecutor(new TuViCommand(plugin));
    }
}

class TuViCommand implements CommandExecutor {

    private final AoiMain plugin;

    public TuViCommand(AoiMain plugin){this.plugin=plugin;}

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player p)) return true;
        var d = plugin.pm().get(p);
        p.sendMessage("§eTu vi: " + d.getRank() + " - Lv." + d.getLevel());
        return true;
    }
} 
