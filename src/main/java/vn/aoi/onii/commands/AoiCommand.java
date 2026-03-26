package vn.aoi.onii.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import vn.aoi.onii.database.Database;
import vn.aoi.onii.leaderboard.TopGUI;
import vn.aoi.onii.player.*;
import vn.aoi.onii.realm.RealmProgression;
import vn.aoi.onii.shop.ShopManager;

public class AoiCommand implements CommandExecutor {

    private final PlayerManager manager;
    private final Database db;

    public AoiCommand(PlayerManager manager, Database db) {
        this.manager = manager;
        this.db = db;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player player)) return true;

        PlayerData data = manager.get(player.getUniqueId(), player.getName());

        // SHOP
        if (args.length == 1 && args[0].equalsIgnoreCase("shop")) {
            player.openInventory(new ShopManager().createShop(1));
            return true;
        }

        // TOP
        if (args.length == 1 && args[0].equalsIgnoreCase("top")) {
            player.openInventory(new TopGUI(db).create());
            return true;
        }

        // INFO
        if (args.length == 1 && args[0].equalsIgnoreCase("info")) {
            player.sendMessage("【❅】Thông Tin【❅】");
            player.sendMessage("§6Đạo hữu: §f" + data.getName());
            player.sendMessage("§6Cảnh giới: §f" + data.getRealm().getDisplay());
            player.sendMessage("§6Tu vi: §f" + data.getStage().getDisplay());
            player.sendMessage("§6EXP: §f" + data.getExp());
            player.sendMessage("§6Công pháp: §f" + data.getTechnique());
            return true;
        }

        // BREAK
        if (args.length == 1 && args[0].equalsIgnoreCase("break")) {
            int req = RealmProgression.getRequiredExp(data.getRealm(), data.getStage());

            if (data.getExp() < req) {
                player.sendMessage("§cChưa đủ EXP!");
                return true;
            }

            var nextStage = RealmProgression.nextStage(data.getStage());

            if (nextStage != null) {
                data.setStage(nextStage);
            } else {
                var nextRealm = RealmProgression.nextRealm(data.getRealm());
                if (nextRealm != null) {
                    data.setRealm(nextRealm);
                    data.setStage(vn.aoi.onii.enums.Stage.SO_KY);
                }
            }

            data.setExp(0);
            manager.save(data);

            player.sendMessage("§aĐột phá thành công!");
            return true;
        }

        // EXP
        if (args.length == 2 && args[0].equalsIgnoreCase("addexp")) {
            int exp = Integer.parseInt(args[1]);
            data.setExp(data.getExp() + exp);
            manager.save(data);
            player.sendMessage("§a+" + exp + " EXP");
            return true;
        }

        player.sendMessage("/aoi shop | top | break | addexp");
        return true;
    }
}
