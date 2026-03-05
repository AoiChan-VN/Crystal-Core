package aoichan.crystal.commands;

import aoichan.crystal.AoiMain;
import aoichan.crystal.core.manager.GemsManager;
import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

// [!] Code: Main Gems Command System
public class GemsCommand implements CommandExecutor, TabCompleter {

    private final AoiMain plugin;
    private final GemsManager gemsManager;

    public GemsCommand(AoiMain plugin) {
        this.plugin = plugin;
        this.gemsManager = plugin.getGemsManager();
    }

    // [!] Code: Command Handler
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 0) {

            if (!(sender instanceof Player)) {
                sender.sendMessage("Console must specify player.");
                return true;
            }

            Player player = (Player) sender;
            int gems = gemsManager.getGems(player);

            player.sendMessage("§b[Gems] §fYou have §e" + gems + " §fgems.");
            return true;
        }

        switch (args[0].toLowerCase()) {

            case "give":
                return handleGive(sender, args);

            case "take":
                return handleTake(sender, args);

            case "set":
                return handleSet(sender, args);

            case "reload":
                return handleReload(sender);

            case "info":
                return handleInfo(sender, args);

            default:
                sender.sendMessage("§cUnknown subcommand.");
        }

        return true;
    }

    // [!] Code: Give Gems
    private boolean handleGive(CommandSender sender, String[] args) {

        if (!sender.hasPermission("crystal.gems.give")) {
            sender.sendMessage("§cNo permission.");
            return true;
        }

        if (args.length < 3) {
            sender.sendMessage("§cUsage: /gems give <player> <amount>");
            return true;
        }

        Player target = Bukkit.getPlayer(args[1]);
        int amount = Integer.parseInt(args[2]);

        if (target == null) {
            sender.sendMessage("§cPlayer not found.");
            return true;
        }

        gemsManager.addGems(target, amount);

        sender.sendMessage("§aAdded gems.");
        target.sendMessage("§aYou received " + amount + " gems.");

        return true;
    }

    // [!] Code: Take Gems
    private boolean handleTake(CommandSender sender, String[] args) {

        if (!sender.hasPermission("crystal.gems.take")) {
            sender.sendMessage("§cNo permission.");
            return true;
        }

        if (args.length < 3) {
            sender.sendMessage("§cUsage: /gems take <player> <amount>");
            return true;
        }

        Player target = Bukkit.getPlayer(args[1]);
        int amount = Integer.parseInt(args[2]);

        if (target == null) {
            sender.sendMessage("§cPlayer not found.");
            return true;
        }

        gemsManager.removeGems(target, amount);

        sender.sendMessage("§aRemoved gems.");
        target.sendMessage("§cYou lost " + amount + " gems.");

        return true;
    }

    // [!] Code: Set Gems
    private boolean handleSet(CommandSender sender, String[] args) {

        if (!sender.hasPermission("crystal.gems.set")) {
            sender.sendMessage("§cNo permission.");
            return true;
        }

        if (args.length < 3) {
            sender.sendMessage("§cUsage: /gems set <player> <amount>");
            return true;
        }

        Player target = Bukkit.getPlayer(args[1]);
        int amount = Integer.parseInt(args[2]);

        if (target == null) {
            sender.sendMessage("§cPlayer not found.");
            return true;
        }

        gemsManager.setGems(target, amount);

        sender.sendMessage("§aSet gems.");
        return true;
    }

    // [!] Code: Reload Command
    private boolean handleReload(CommandSender sender) {

        if (!sender.hasPermission("crystal.gems.reload")) {
            sender.sendMessage("§cNo permission.");
            return true;
        }

        plugin.reloadConfig();
        plugin.getLangManager().loadLanguage();

        sender.sendMessage("§aCrystal Ultimate reloaded.");
        return true;
    }

    // [!] Code: Info Command
    private boolean handleInfo(CommandSender sender, String[] args) {

        if (args.length < 2) {
            sender.sendMessage("§cUsage: /gems info <player>");
            return true;
        }

        Player target = Bukkit.getPlayer(args[1]);

        if (target == null) {
            sender.sendMessage("§cPlayer not found.");
            return true;
        }

        int gems = gemsManager.getGems(target);

        sender.sendMessage("§e" + target.getName() + " has " + gems + " gems.");

        return true;
    }

    // [!] Code: Tab Complete
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        List<String> list = new ArrayList<>();

        if (args.length == 1) {
            list.add("give");
            list.add("take");
            list.add("set");
            list.add("reload");
            list.add("info");
        }

        if (args.length == 2) {

            for (Player p : Bukkit.getOnlinePlayers()) {
                list.add(p.getName());
            }
        }

        return list;
    }
}
