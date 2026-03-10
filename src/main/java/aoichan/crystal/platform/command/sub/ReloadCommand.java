package aoichan.crystal.platform.command.sub;

import aoichan.crystal.bootstrap.CrystalPlugin;
import org.bukkit.command.CommandSender;

public class ReloadCommand implements SubCommand {

    @Override
    public void execute(CommandSender sender, String[] args) {

        // [!] Code: Reload config
        CrystalPlugin.get().reloadConfig();

        sender.sendMessage("§aCrystal config reloaded.");
    }
} 
