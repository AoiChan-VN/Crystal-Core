package aoichan.crystal.gameplay.gem;

import aoichan.crystal.bootstrap.AoiMain;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class GemManager {

    private static final Map<String, Gem> gems = new HashMap<>();

    private static File file;
    private static YamlConfiguration config;

    public static void load() {

        // 【!】Code: load file gems.yml
        file = new File(AoiMain.get().getDataFolder(), "gems.yml");

        if (!file.exists()) {
            AoiMain.get().saveResource("gems.yml", false);
        }

        config = YamlConfiguration.loadConfiguration(file);

        gems.clear();

        for (String id : config.getKeys(false)) {

            String name = config.getString(id + ".name");

            Map<GemStat, Double> stats = new HashMap<>();

            if (config.contains(id + ".stats")) {

                for (String statKey : config.getConfigurationSection(id + ".stats").getKeys(false)) {

                    GemStat stat = GemStat.valueOf(statKey.toUpperCase());

                    double value = config.getDouble(id + ".stats." + statKey);

                    stats.put(stat, value);

                }

            }

            Gem gem = new Gem(id, name, stats);

            gems.put(id, gem);

        }

    }

    public static Gem getGem(String id) {

        return gems.get(id);

    }

    public static Map<String, Gem> getAll() {

        return gems;

    }

}
