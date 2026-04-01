package vn.aoi.onii.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import vn.aoi.onii.config.ConfigManager;
import vn.aoi.onii.manager.PlayerManager;

public class MobKillListener implements Listener {

    @EventHandler
    public void onKill(EntityDeathEvent e) {
        if (e.getEntity().getKiller() == null) return;

        Player player = e.getEntity().getKiller();
        String type = e.getEntity().getType().name();

        if (!ConfigManager.mobs.contains(type)) return;

        int exp = ConfigManager.mobs.getInt(type);
        PlayerManager.addExp(player, exp);
    }
} 
