package vn.aoi.onii.combat.listener;

import org.bukkit.event.*;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.entity.*;

import vn.aoi.onii.player.PlayerManager;
import vn.aoi.onii.combat.*;

public class CombatListener implements Listener {

    private final PlayerManager playerManager;

    public CombatListener(PlayerManager pm) {
        this.playerManager = pm;
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent e) {

        if (!(e.getDamager() instanceof Player attacker)) return;
        if (!(e.getEntity() instanceof LivingEntity target)) return;

        var attackerData = playerManager.get(attacker);
        if (attackerData == null) return;

        StatProfile atk = attackerData.getStats();

        StatProfile def = new StatProfile();

        if (target instanceof Player tp) {
            var d = playerManager.get(tp);
            if (d != null) def = d.getStats();
        }

        DamageResult result = DamageEngine.calculate(atk, def, e.getDamage());

        e.setDamage(result.damage);

        if (result.crit) {
            attacker.sendMessage("CRIT! " + (int) result.damage);
        }
    }
} 
