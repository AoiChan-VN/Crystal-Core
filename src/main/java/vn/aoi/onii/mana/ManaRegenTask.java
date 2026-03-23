// =========================
//      MANA REGEN TASK
// =========================
package vn.aoi.onii.mana;

import org.bukkit.Bukkit;
import vn.aoi.onii.Main;
import vn.aoi.onii.player.PlayerManager;

public class ManaRegenTask {

    public ManaRegenTask(Main plugin, PlayerManager pm) {
        Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            pm.getOnline().forEach(data -> data.regenMana(2));
        }, 40, 40);
    }
} 
