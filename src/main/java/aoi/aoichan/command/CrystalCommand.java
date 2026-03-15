package aoi.aoichan.command;

import aoi.aoichan.AoiMain;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/*
 Command chính của CrystalEngine
*/

public class CrystalCommand implements CommandExecutor {

    private final AoiMain plugin;

    public CrystalCommand(AoiMain plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 0) {

            sender.sendMessage("§bCrystalEngine §7v1.0");
            return true;
        }

        if (args[0].equalsIgnoreCase("reload")) {

            plugin.getReloadManager().reload();

            sender.sendMessage("§aCrystalEngine đã reload.");
            return true;
        }

        return true;
    }
}
