package aoichan.crystal.commands.sub;

import aoichan.crystal.AoiMain;
import aoichan.crystal.core.ReloadManager;
import org.bukkit.command.CommandSender;

public class ReloadSub implements SubCommand {

    private final AoiMain plugin;

    public ReloadSub(AoiMain plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getName() {
        return "reload";
    }

    @Override
    public String getPermission() {
        return "gems.reload";
    }

    @Override
    public boolean isPlayerOnly() {
        return false;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        ReloadManager.reload(plugin);
        sender.sendMessage("Gems reloaded.");
    }
} 
