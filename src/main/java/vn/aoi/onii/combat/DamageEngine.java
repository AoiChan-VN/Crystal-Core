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
        double def = defender.get(StatType.DEF);
        double critChance = attacker.get(StatType.CRIT);

        double damage = base + (str * 0.7) + (agi * 0.3);

        damage *= (100.0 / (100.0 + def));

        boolean crit = ThreadLocalRandom.current().nextDouble() < critChance;
        if (crit) damage *= 1.8;

        ElementType element = rollElement(atkElem);

        double atkElement = atkElem.getAttack(element);
        double defElement = defElem.getResist(element);

        double elementMultiplier = 1.0 + (atkElement - defElement) / 100.0;

        damage *= elementMultiplier;

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
}
