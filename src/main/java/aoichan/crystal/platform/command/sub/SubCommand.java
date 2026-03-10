package aoichan.crystal.platform.command.sub;

import org.bukkit.command.CommandSender;

public interface SubCommand {

    // [!] Code: Execute command
    void execute(CommandSender sender, String[] args);
} 
