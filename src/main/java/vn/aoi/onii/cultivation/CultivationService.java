package vn.aoi.onii.cultivation;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import vn.aoi.onii.Main;
import vn.aoi.onii.events.BreakthroughEvent;
import vn.aoi.onii.events.LevelUpEvent;
import vn.aoi.onii.player.PlayerData;

public class CultivationService {

    private final Main plugin;

    public CultivationService(Main plugin) {
        this.plugin = plugin;
    }

    public void addExp(Player player, int amount) {
        PlayerData data = plugin.getPlayerManager().get(player);
        if (data == null) return;

        data.addExp(amount);

        while (true) {

            Realm realm = plugin.getRealmManager().getRealm(data.getRealm());
            if (realm == null) return;

            if (data.getLevel() >= realm.getMaxLevel()) {

                String next = realm.getNextRank();
                if (next == null) return;

                data.setRealm(next);
                data.setLevel(1);
                data.setExp(0);

                Bukkit.getPluginManager().callEvent(
                        new BreakthroughEvent(player, next)
                );

                player.sendMessage("§6Đột phá: " + next);
                return;
            }

            int required = realm.getRequiredExp(data.getLevel());

            if (data.getExp() < required) return;

            data.setExp(data.getExp() - required);
            data.setLevel(data.getLevel() + 1);

            Bukkit.getPluginManager().callEvent(
                    new LevelUpEvent(player, data.getLevel())
            );

            player.sendMessage("§aLevel Up: " + data.getLevel());
        }
    }
}
