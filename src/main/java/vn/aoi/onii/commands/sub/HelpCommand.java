package vn.aoi.onii.commands.sub;

import org.bukkit.command.CommandSender;
import vn.aoi.onii.commands.core.AbstractCommand;
import vn.aoi.onii.commands.core.CommandManager;

import java.util.*;

public class HelpCommand extends AbstractCommand {

    private final CommandManager manager;

    public HelpCommand(CommandManager manager) {
        this.manager = manager;
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "Hiển thị trợ giúp";
    }

    @Override
    public String getUsage() {
        return "/aoi help";
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        sender.sendMessage("§a=== AOI COMMANDS ===");

        for (var cmd : manager.getCommands()) {
            if (cmd.getPermission() == null || sender.hasPermission(cmd.getPermission())) {
                sender.sendMessage("§e" + cmd.getUsage() + " §7- " + cmd.getDescription());
            }
        }

        return true;
    }

    @Override
    public List<String> tab(CommandSender sender, String[] args) {
        return Collections.emptyList();
    }
}
