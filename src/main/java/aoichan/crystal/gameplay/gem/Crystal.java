package aoichan.crystal.gameplay.gem;

import java.util.Map;

public class Crystal {

    private final String id;
    private final String name;
    private final Map<CrystalStat, Double> stats;

    public Crystal(String id, String name, Map<CrystalStat, Double> stats) {

        // 【!】Code: id của gem
        this.id = id;

        // 【!】Code: tên hiển thị
        this.name = name;

        // 【!】Code: stat của gem
        this.stats = stats;

    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Map<CrystalStat, Double> getStats() {
        return stats;
    }

}
