package vn.aoi.onii.commands.sub;

import org.bukkit.command.CommandSender;
import vn.aoi.onii.commands.core.AbstractCommand;

import java.util.Collections;
import java.util.List;

public class InfoCommand extends AbstractCommand {

    @Override
    public String getName() {
        return "info";
    }

    @Override
    public String getDescription() {
        return "Xem thông tin";
    }

    @Override
    public String getUsage() {
        return "/aoi info";
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        sender.sendMessage("§aThông tin player...");
        return true;
    }

    @Override
    public List<String> tab(CommandSender sender, String[] args) {
        return Collections.emptyList();
    }
} 
