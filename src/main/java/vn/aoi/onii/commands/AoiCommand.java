package vn.aoi.onii.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import vn.aoi.onii.player.PlayerManager;
import vn.aoi.onii.shop.ShopManager;

public class AoiCommand implements CommandExecutor {

    private final PlayerManager manager;

    public AoiCommand(PlayerManager manager) {
        this.manager = manager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player p)) return true;

        if (args.length == 1 && args[0].equalsIgnoreCase("shop")) {
            p.openInventory(new ShopManager().createShop(1));
            return true;
        }

        if (args.length == 2 && args[0].equalsIgnoreCase("info")) {

            Player t = Bukkit.getPlayer(args[1]);
            if (t == null) return true;

            var d = manager.get(t.getUniqueId(), t.getName());

            sender.sendMessage("§6Đạo hữu: §f" + d.getName());
            sender.sendMessage("§6Cảnh giới: §f" + d.getRealm().getDisplay());
            sender.sendMessage("§6Tu vi: §f" + d.getStage().getDisplay());
            sender.sendMessage("§6Công pháp: §f" + d.getTechnique());
            return true;
        }

        return true;
    }
}
