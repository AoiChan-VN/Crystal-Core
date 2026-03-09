package aoichan.crystal.api.gem;

import java.util.List;
import java.util.Map;

public class GemDefinition {

    // [!] Code: Gem ID
    private final String id;

    // [!] Code: Display Name
    private final String name;

    // [!] Code: Rarity
    private final String rarity;

    // [!] Code: Element
    private final String element;

    // [!] Code: Tier
    private final int tier;

    // [!] Code: Forge success rate
    private final double successRate;

    // [!] Code: Stats bonus
    private final Map<String, Double> stats;

    // [!] Code: Effects list
    private final List<String> effects;

    // [!] Code: Lore lines
    private final List<String> lore;

    public GemDefinition(
            String id,
            String name,
            String rarity,
            String element,
            int tier,
            double successRate,
            Map<String, Double> stats,
            List<String> effects,
            List<String> lore
    ) {
        this.id = id;
        this.name = name;
        this.rarity = rarity;
        this.element = element;
        this.tier = tier;
        this.successRate = successRate;
        this.stats = stats;
        this.effects = effects;
        this.lore = lore;
    }

    // [!] Code: Getters

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRarity() {
        return rarity;
    }

    public String getElement() {
        return element;
    }

    public int getTier() {
        return tier;
    }

    public double getSuccessRate() {
        return successRate;
    }

    public Map<String, Double> getStats() {
        return stats;
    }

    public List<String> getEffects() {
        return effects;
    }

    public List<String> getLore() {
        return lore;
    }
}
