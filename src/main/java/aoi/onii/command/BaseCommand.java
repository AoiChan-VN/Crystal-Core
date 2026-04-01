package vn.aoi.onii.command;

import org.bukkit.command.CommandSender;

public abstract class BaseCommand {
    public abstract String getName();
    public abstract void execute(CommandSender sender, String[] args);
} 
