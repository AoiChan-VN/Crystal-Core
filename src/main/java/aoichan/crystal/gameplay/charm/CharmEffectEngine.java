package aoichan.crystal.gameplay.charm;

public class CharmEffectEngine {

    // [!] Code: Apply success bonus
    public static double applyChance(
            double baseChance,
            CharmData charm
    ) {

        if (charm == null) return baseChance;

        return baseChance + charm.getSuccessBonus();
    }

} 
