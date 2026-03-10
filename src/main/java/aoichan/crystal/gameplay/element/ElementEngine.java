package aoichan.crystal.gameplay.element;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

// [!] Code: Element combat engine
public class ElementEngine {

    public static double applyElementDamage(

            Player player,
            LivingEntity target,
            double damage,
            ElementType element

    ) {

        double newDamage =
                ElementDamageCalculator.applyElementBonus(
                        damage,
                        element
                );

        return newDamage;
    }

}
