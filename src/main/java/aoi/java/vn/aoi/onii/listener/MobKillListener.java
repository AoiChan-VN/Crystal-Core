package vn.aoi.onii.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import vn.aoi.onii.manager.CultivationService;
import vn.aoi.onii.manager.PlayerManager;

public class MobKillListener implements Listener {

    private final PlayerManager playerManager;
    private final CultivationService cultivationService;

    public MobKillListener(PlayerManager playerManager, CultivationService cultivationService) {
        this.playerManager = playerManager;
        this.cultivationService = cultivationService;
    }

    @EventHandler
    public void onKill(EntityDeathEvent event) {
        Player killer = event.getEntity().getKiller();
        if (killer == null) return;

        var cultivator = playerManager.get(killer.getUniqueId());
        if (cultivator == null) return;

        double exp = 5; // TODO: load from config mobs-exp

        cultivationService.addExp(cultivator, exp);
    }
} 
