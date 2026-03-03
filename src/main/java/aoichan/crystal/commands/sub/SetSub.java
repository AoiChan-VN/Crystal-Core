package aoichan.crystal.commands.sub;

import aoichan.crystal.AoiMain;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSub implements SubCommand {

    private final AoiMain plugin;

    public SetSub(AoiMain plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getName() {
        return "set";
    }

    @Override
    public String getPermission() {
        return "gems.set";
    }

    @Override
    public boolean isPlayerOnly() {
        return false;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

        if (args.length < 3) {
            sender.sendMessage("Usage: /gems set <player> <type> <amount>");
            return;
        }

        Player target = Bukkit.getPlayerExact(args[0]);
        if (target == null) {
            sender.sendMessage("Player not found.");
            return;
        }

        String type = args[1];

        int amount;
        try {
            amount = Integer.parseInt(args[2]);
        } catch (NumberFormatException e) {
            sender.sendMessage("Amount must be a number.");
            return;
        }

        plugin.getGemsManager().setGem(target.getUniqueId(), type, amount);

        sender.sendMessage("Set " + type + " of " + target.getName() + " to " + amount);
    }
} 
