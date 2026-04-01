package vn.aoi.onii.commands;

import org.bukkit.command.*;
import org.bukkit.entity.Player;
import vn.aoi.onii.Main;
import vn.aoi.onii.player.PlayerData;

public class CultivationCommand implements CommandExecutor {

    private final Main plugin;

    public CultivationCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player player)) return true;

        PlayerData data = plugin.getPlayerManager().get(player);

        player.sendMessage("§6Cảnh giới: §e" + data.getRealm());
        player.sendMessage("§6Tầng: §e" + data.getLevel());
        player.sendMessage("§6EXP: §e" + data.getExp());

        return true;
    }
} 
