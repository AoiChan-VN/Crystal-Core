package vn.aoi.onii.combat.listener;

import org.bukkit.event.*;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.entity.*;

import vn.aoi.onii.player.PlayerManager;
import vn.aoi.onii.combat.*;

public class AdvancedCombatListener implements Listener {

    private final PlayerManager playerManager;

    public AdvancedCombatListener(PlayerManager pm) {
        this.playerManager = pm;
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent e) {

        if (!(e.getDamager() instanceof Player attacker)) return;
        if (!(e.getEntity() instanceof LivingEntity target)) return;

        var attackerData = playerManager.get(attacker);
        if (attackerData == null) return;

        StatProfile atk = attackerData.getStats();
        ElementProfile atkElem = new ElementProfile();

        StatProfile def = new StatProfile();
        ElementProfile defElem = new ElementProfile();

        if (target instanceof Player tp) {
            var d = playerManager.get(tp);
            if (d != null) {
                def = d.getStats();
            }
        }

        AdvancedDamageResult result = AdvancedDamageEngine.calculate(
                atk,
                def,
                atkElem,
                defElem,
                e.getDamage()
        );

        e.setDamage(result.finalDamage);

        if (result.crit) {
            attacker.sendMessage("CRIT " + (int) result.finalDamage);
        }

        if (result.element != ElementType.NONE) {
            attacker.sendMessage("ELEMENT " + result.element.name());
        }
    }
} 
