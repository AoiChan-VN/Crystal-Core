package vn.aoi.onii.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import vn.aoi.onii.manager.CultivationService;

public class MobKillListener implements Listener {

    private final CultivationService cultivationService;

    public MobKillListener(CultivationService cultivationService) {
        this.cultivationService = cultivationService;
    }

    @EventHandler
    public void onKill(EntityDeathEvent event) {
        Player killer = event.getEntity().getKiller();
        if (killer == null) return;

        double exp = 5; // TODO: load config

        cultivationService.addExp(killer, exp);
    }
}
