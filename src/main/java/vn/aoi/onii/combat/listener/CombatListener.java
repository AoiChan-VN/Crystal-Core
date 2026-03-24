package vn.aoi.onii.combat.listener;

import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import vn.aoi.onii.combat.*;
import vn.aoi.onii.player.PlayerManager;

public class CombatListener implements Listener {

    private final PlayerManager playerManager;
    private final BuffManager buffManager = new BuffManager();

    public CombatListener(PlayerManager pm) {
        this.playerManager = pm;
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent e) {

        if (!(e.getDamager() instanceof Player attacker)) return;
        if (!(e.getEntity() instanceof LivingEntity target)) return;

        var attackerData = playerManager.get(attacker);
        if (attackerData == null) return;

        StatProfile atkBase = attackerData.getStats();
        StatProfile atk = buffManager.apply(attacker.getUniqueId(), atkBase);

        ElementProfile atkElem = attackerData.getElements();

        StatProfile def = new StatProfile();
        ElementProfile defElem = new ElementProfile();

        if (target instanceof Player tp) {
            var d = playerManager.get(tp);
            if (d != null) {
                def = buffManager.apply(tp.getUniqueId(), d.getStats());
                defElem = d.getElements();
            }
        }

        DamageResult result = DamageEngine.calculate(
                atk,
                def,
                atkElem,
                defElem,
                e.getDamage()
        );

        e.setDamage(result.damage);

        if (result.crit) attacker.sendMessage("CRIT " + (int) result.damage);

        if (result.element != ElementType.NONE) {
            attacker.sendMessage("ELEMENT " + result.element.name());
        }
    }
}
