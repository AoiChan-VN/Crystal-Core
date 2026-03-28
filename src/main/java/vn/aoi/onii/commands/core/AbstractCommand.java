package vn.aoi.onii.commands.core;

import org.bukkit.command.CommandSender;

public abstract class AbstractCommand implements SubCommand {

    @Override
    public String getPermission() {
        return null;
    }

    public boolean hasPermission(CommandSender sender) {
        return getPermission() == null || sender.hasPermission(getPermission());
    }
} 
