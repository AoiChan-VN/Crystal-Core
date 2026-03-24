package vn.aoi.onii.combat;

import java.util.concurrent.ThreadLocalRandom;

public class DamageEngine {

    public static DamageResult calculate(
            StatProfile attacker,
            StatProfile defender,
            ElementProfile atkElem,
            ElementProfile defElem,
            double base
    ) {

        DamageResult result = new DamageResult();

        double str = attacker.get(StatType.STR);
        double agi = attacker.get(StatType.AGI);
        double intel = attacker.get(StatType.INT);

        double def = defender.get(StatType.DEF);

        double critChance = attacker.get(StatType.CRIT);
        critChance = Math.max(0, Math.min(critChance, 1));

        double damage = base + (str * 0.6) + (agi * 0.25) + (intel * 0.15);

        double penetration = agi * 0.002;
        def = CombatStats.applyPenetration(def, penetration);

        damage = CombatStats.applyDefense(damage, def);

        boolean crit = ThreadLocalRandom.current().nextDouble() < critChance;
        if (crit) damage *= (1.5 + (agi * 0.001));

        ElementType element = rollElement(atkElem);

        double atkElement = atkElem.getAttack(element);
        double defElement = defElem.getResist(element);

        double elementBonus = (atkElement - defElement) / 100.0;

        double counter = elementCounter(element);

        damage *= (1.0 + elementBonus) * counter;

        double lifesteal = str * 0.001;

        damage = Math.max(0, damage);

        result.damage = damage;
        result.crit = crit;
        result.element = element;

        return result;
    }

    private static ElementType rollElement(ElementProfile profile) {
        double total = 0;
        for (ElementType t : ElementType.values()) total += profile.getAttack(t);

        if (total <= 0) return ElementType.NONE;

        double roll = ThreadLocalRandom.current().nextDouble() * total;
        double current = 0;

        for (ElementType t : ElementType.values()) {
            current += profile.getAttack(t);
            if (roll <= current) return t;
        }

        return ElementType.NONE;
    }

    private static double elementCounter(ElementType type) {
        return switch (type) {
            case FIRE -> 1.1;
            case WATER -> 1.1;
            case EARTH -> 1.05;
            case WIND -> 1.05;
            default -> 1.0;
        };
    }
}
