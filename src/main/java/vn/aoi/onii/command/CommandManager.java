package vn.aoi.onii.command;

import org.bukkit.command.*;
import vn.aoi.onii.Main;
import vn.aoi.onii.command.sub.*;
import java.util.*;

public class CommandManager implements CommandExecutor {

    private static final Map<String,BaseCommand> map=new HashMap<>();

    public static void init(Main p){
        var cmd=p.getCommand("aoi");
        if(cmd!=null) cmd.setExecutor(new CommandManager());

        register(new ReloadCommand());
        register(new InfoCommand());
    }

    private static void register(BaseCommand c){
        map.put(c.name(),c);
    }

    public boolean onCommand(CommandSender s,Command c,String l,String[] a){
        if(a.length==0) return false;
        var sub=map.get(a[0].toLowerCase());
        if(sub==null) return false;
        sub.run(s,a);
        return true;
    }
} 
