package vn.aoi.onii.commands.core;

import org.bukkit.command.*;

import java.util.*;

public class CommandManager implements CommandExecutor, TabCompleter {

    private final Map<String, SubCommand> subCommands = new HashMap<>();

    public void register(SubCommand cmd) {
        subCommands.put(cmd.getName().toLowerCase(), cmd);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 0) {
            sender.sendMessage("§e/aoi help");
            return true;
        }

        SubCommand sub = subCommands.get(args[0].toLowerCase());

        if (sub == null) {
            sender.sendMessage("§cUnknown command!");
            return true;
        }

        if (sub instanceof AbstractCommand ac && !ac.hasPermission(sender)) {
            sender.sendMessage("§cBạn không có quyền!");
            return true;
        }

        return sub.execute(sender, Arrays.copyOfRange(args, 1, args.length));
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        if (args.length == 1) {
            List<String> list = new ArrayList<>();

            for (SubCommand cmd : subCommands.values()) {
                if (cmd.getPermission() == null || sender.hasPermission(cmd.getPermission())) {
                    list.add(cmd.getName());
                }
            }

            list.removeIf(s -> !s.startsWith(args[0].toLowerCase()));
            return list;
        }

        SubCommand sub = subCommands.get(args[0].toLowerCase());

        if (sub != null) {
            return sub.tab(sender, Arrays.copyOfRange(args, 1, args.length));
        }

        return Collections.emptyList();
    }

    public Collection<SubCommand> getCommands() {
        return subCommands.values();
    }
}
