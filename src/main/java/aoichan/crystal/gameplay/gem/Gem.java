package aoichan.crystal.gameplay.gem;

import java.util.Map;

public class Gem {

    private final String id;
    private final String name;
    private final GemRarity rarity;
    private final Map<GemStat, Double> stats;

    public Gem(String id, String name, GemRarity rarity, Map<GemStat, Double> stats) {

        // 【!】Code: id gem
        this.id = id;

        // 【!】Code: tên gem
        this.name = name;

        // 【!】Code: rarity gem
        this.rarity = rarity;

        // 【!】Code: stat gem
        this.stats = stats;

    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public GemRarity getRarity() {
        return rarity;
    }

    public Map<GemStat, Double> getStats() {
        return stats;
    }

    }
