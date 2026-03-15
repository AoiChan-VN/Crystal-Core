package aoi.aoichan.command;

import aoi.aoichan.config.ConfigManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class EngineCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(args.length == 1){

            if(args[0].equalsIgnoreCase("reload")){

                // 【!】Code: reload config
                ConfigManager.reload();

                sender.sendMessage("AoiEngine config reloaded.");

                return true;
            }

        }

        sender.sendMessage("/aoiengine reload");

        return true;
    }
} 
