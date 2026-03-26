package vn.aoi.onii.quest;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.event.*;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.entity.Player;
import vn.aoi.onii.player.PlayerManager;

public class QuestListener implements Listener {

    private final QuestManager questManager;
    private final PlayerManager playerManager;
    private final Economy econ;

    public QuestListener(QuestManager qm, PlayerManager pm, Economy econ) {
        this.questManager = qm;
        this.playerManager = pm;
        this.econ = econ;
    }

    @EventHandler
    public void kill(EntityDeathEvent e) {
        if (!(e.getEntity().getKiller() instanceof Player player)) return;

        var config = questManager.getConfig();

        for (String key : config.getConfigurationSection("quests").getKeys(false)) {

            String path = "quests." + key;

            if (!config.getString(path + ".type").equalsIgnoreCase("KILL")) continue;

            if (!e.getEntity().getType().name().equalsIgnoreCase(config.getString(path + ".target"))) continue;

            questManager.addProgress(player.getUniqueId(), key, 1);

            int progress = questManager.getProgress(player.getUniqueId(), key);
            int need = config.getInt(path + ".amount");

            if (progress >= need) {

                double money = config.getDouble(path + ".reward.money");
                int exp = config.getInt(path + ".reward.exp");

                econ.depositPlayer(player, money);

                var data = playerManager.get(player.getUniqueId(), player.getName());
                data.setExp(data.getExp() + exp);
                playerManager.save(data);

                player.sendMessage("§aHoàn thành nhiệm vụ! +" + exp + " EXP");

                questManager.reset(player.getUniqueId(), key);
            }
        }
    }

    @EventHandler
    public void breakBlock(BlockBreakEvent e) {

        Player player = e.getPlayer();
        var config = questManager.getConfig();

        for (String key : config.getConfigurationSection("quests").getKeys(false)) {

            String path = "quests." + key;

            if (!config.getString(path + ".type").equalsIgnoreCase("BREAK")) continue;

            if (!e.getBlock().getType().name().equalsIgnoreCase(config.getString(path + ".target"))) continue;

            questManager.addProgress(player.getUniqueId(), key, 1);

            int progress = questManager.getProgress(player.getUniqueId(), key);
            int need = config.getInt(path + ".amount");

            if (progress >= need) {

                double money = config.getDouble(path + ".reward.money");
                int exp = config.getInt(path + ".reward.exp");

                econ.depositPlayer(player, money);

                var data = playerManager.get(player.getUniqueId(), player.getName());
                data.setExp(data.getExp() + exp);
                playerManager.save(data);

                player.sendMessage("§aHoàn thành nhiệm vụ! +" + exp + " EXP");

                questManager.reset(player.getUniqueId(), key);
            }
        }
    }
} 
