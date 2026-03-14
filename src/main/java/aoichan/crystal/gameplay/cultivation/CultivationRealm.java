package aoichan.crystal.gameplay.cultivation;

import aoichan.crystal.gameplay.stat.StatType;

import java.util.Map;

public class CultivationRealm {

    private final String id;
    private final String name;

    private final Map<StatType, Double> bonuses;

    public CultivationRealm(String id,
                            String name,
                            Map<StatType, Double> bonuses) {

        // 【!】Code: id realm
        this.id = id;

        // 【!】Code: tên cảnh giới
        this.name = name;

        // 【!】Code: stat bonus
        this.bonuses = bonuses;

    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Map<StatType, Double> getBonuses() {
        return bonuses;
    }

} 
