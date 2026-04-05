package vn.aoi.onii.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import vn.aoi.onii.manager.CultivationService;
import vn.aoi.onii.manager.PlayerManager;
import vn.aoi.onii.model.Cultivator;

@CommandAlias("aoi")
public class AoiCommand extends BaseCommand {

    private final PlayerManager playerManager;
    private final CultivationService service;

    public AoiCommand(PlayerManager playerManager, CultivationService service) {
        this.playerManager = playerManager;
        this.service = service;
    }

    // ================= PLAYER =================

    @Subcommand("info")
    @CommandPermission("aoi.player")
    public void onInfo(Player player) {

        Cultivator c = playerManager.get(player.getUniqueId());

        if (c == null) {
            player.sendMessage("§cChưa load!");
            return;
        }

        player.sendMessage("§6=== TU VI ===");
        player.sendMessage("§eCảnh giới: §f" + c.getRealm());
        player.sendMessage("§eLevel: §f" + c.getLevel());
        player.sendMessage("§eEXP: §f" + c.getExp());
    }

    // ================= ADMIN =================

    @Subcommand("addexp")
    @CommandPermission("aoi.admin")
    @CommandCompletion("@players @range:1-100000")
    public void onAddExp(CommandSender sender, String targetName, double amount) {

        Player target = Bukkit.getPlayer(targetName);
        if (target == null) {
            sender.sendMessage("Player offline!");
            return;
        }

        service.addExp(target, amount);
        sender.sendMessage("§aĐã thêm EXP!");
    }

    @Subcommand("setexp")
    @CommandPermission("aoi.admin")
    public void onSetExp(CommandSender sender, String targetName, double amount) {

        Player target = Bukkit.getPlayer(targetName);
        if (target == null) {
            sender.sendMessage("Player offline!");
            return;
        }

        var c = playerManager.get(target.getUniqueId());
        if (c != null) {
            c.setExp(amount);
        }

        sender.sendMessage("§aSet EXP!");
    }

    @Subcommand("setrealm")
    @CommandPermission("aoi.admin")
    public void onSetRealm(CommandSender sender, String targetName, String realm) {

        Player target = Bukkit.getPlayer(targetName);
        if (target == null) {
            sender.sendMessage("Player offline!");
            return;
        }

        var c = playerManager.get(target.getUniqueId());
        if (c != null) {
            c.setRealm(realm);
            c.setLevel(1);
            c.setExp(0);
        }

        sender.sendMessage("§aSet Realm!");
    }

    @Subcommand("reset")
    @CommandPermission("aoi.admin")
    public void onReset(CommandSender sender, String targetName) {

        Player target = Bukkit.getPlayer(targetName);
        if (target == null) {
            sender.sendMessage("Player offline!");
            return;
        }

        var c = playerManager.get(target.getUniqueId());
        if (c != null) {
            c.setRealm("Phàm nhân");
            c.setLevel(1);
            c.setExp(0);
        }

        sender.sendMessage("§aReset!");
    }
} 
