package aoichan.crystal.platform.listener;

import aoichan.crystal.gameplay.effect.EffectEngine;
import aoichan.crystal.gameplay.effect.GemEffect;
import aoichan.crystal.gameplay.effect.EffectType;
import aoichan.crystal.gameplay.element.ElementEngine;
import aoichan.crystal.gameplay.element.ElementType;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

// [!] Code: Combat trigger listener
public class EffectTriggerListener implements Listener {

    @EventHandler
    public void onAttack(EntityDamageByEntityEvent event) {

        if (!(event.getDamager() instanceof Player player))
            return;

        if (!(event.getEntity() instanceof LivingEntity target))
            return;

        // [!] Code: Element example
        ElementType element = ElementType.THUNDER;

        double damage =
                ElementEngine.applyElementDamage(
                        player,
                        target,
                        event.getDamage(),
                        element
                );

        event.setDamage(damage);

        // [!] Code: Effect example
        GemEffect effect =
                new GemEffect(
                        EffectType.LIGHTNING,
                        0.2,
                        1
                );

        EffectEngine.trigger(
                player,
                target,
                effect
        );
    }
}
