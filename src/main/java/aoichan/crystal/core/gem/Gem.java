package aoichan.crystal.core.gem;

import aoichan.crystal.gameplay.element.ElementType;

import java.util.Map;
import java.util.List;

// [!] Code: Gem data object
public class Gem {

    private final String id;
    private final String name;

    private final ElementType element;
    private final int maxLevel;

    private final Map<String, Double> stats;

    private final List<String> effects;

    public Gem(

            String id,
            String name,
            ElementType element,
            int maxLevel,
            Map<String, Double> stats,
            List<String> effects

    ) {

        this.id = id;
        this.name = name;
        this.element = element;
        this.maxLevel = maxLevel;
        this.stats = stats;
        this.effects = effects;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ElementType getElement() {
        return element;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public Map<String, Double> getStats() {
        return stats;
    }

    public List<String> getEffects() {
        return effects;
    }
} 
