package aoichan.crystal.platform.command;

import aoichan.crystal.bootstrap.CrystalPlugin;
import aoichan.crystal.gameplay.gem.GemItemFactory;
import aoichan.crystal.gameplay.gem.GemModule;
import aoichan.crystal.api.gem.GemDefinition;
import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CrystalCommand implements CommandExecutor {

    // [!] Code: /crystal command
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length < 3) {
            sender.sendMessage("/crystal give <player> <gem>");
            return true;
        }

        if (args[0].equalsIgnoreCase("give")) {

            Player target = Bukkit.getPlayer(args[1]);

            if (target == null) {
                sender.sendMessage("Player not found.");
                return true;
            }

            String gemId = args[2];

            GemModule module = (GemModule)
                    CrystalPlugin.get().getModuleLoader()
                            .getClass(); // placeholder

            // NOTE: phase sau sẽ làm module access sạch hơn

            return true;
        }

        return true;
    }
}
