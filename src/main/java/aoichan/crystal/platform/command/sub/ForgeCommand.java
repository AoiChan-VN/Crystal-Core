package aoichan.crystal.platform.command.sub;

import aoichan.crystal.platform.gui.forge.ForgeGUI;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ForgeCommand implements SubCommand {

    @Override
    public void execute(CommandSender sender, String[] args) {

        if (!(sender instanceof Player player)) {

            sender.sendMessage("Player only.");
            return;
        }

        // [!] Code: Open forge GUI
        new ForgeGUI().open(player);
    }
} 
