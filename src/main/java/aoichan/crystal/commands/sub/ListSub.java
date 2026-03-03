package aoichan.crystal.commands.sub;

import aoichan.crystal.AoiMain;
import org.bukkit.command.CommandSender;

public class ListSub implements SubCommand {

    private final AoiMain plugin;

    public ListSub(AoiMain plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getName() {
        return "list";
    }

    @Override
    public String getPermission() {
        return "gems.list";
    }

    @Override
    public boolean isPlayerOnly() {
        return false;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

        sender.sendMessage("Registered Gems:");
        plugin.getGemsManager().getRegisteredGemTypes()
                .forEach(type -> sender.sendMessage("- " + type));
    }
} 
