package aoichan.crystal.gameplay.stat;

import java.util.EnumMap;
import java.util.Map;

public class PlayerStatProfile {

    private final Map<StatType, Double> stats = new EnumMap<>(StatType.class);

    public PlayerStatProfile() {

        // 【!】Code: khởi tạo stat mặc định
        for (StatType type : StatType.values()) {
            stats.put(type, 0.0);
        }

    }

    public void addStat(StatType type, double value) {

        // 【!】Code: cộng stat
        stats.put(type, stats.getOrDefault(type, 0.0) + value);

    }

    public double getStat(StatType type) {

        return stats.getOrDefault(type, 0.0);

    }

    public Map<StatType, Double> getAll() {

        return stats;

    }

}
 
