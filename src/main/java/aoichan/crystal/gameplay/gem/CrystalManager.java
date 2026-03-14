package aoichan.crystal.gameplay.gem;

import aoichan.crystal.bootstrap.AoiMain;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class CrystalManager {

    private static final Map<String, Crystal> crystals = new HashMap<>();

    private static File file;
    private static YamlConfiguration config;

    public static void load() {

        // 【!】Code: load file gems.yml
        file = new File(AoiMain.get().getDataFolder(), "crystals.yml");

        if (!file.exists()) {
            AoiMain.get().saveResource("crystals.yml", false);
        }

        config = YamlConfiguration.loadConfiguration(file);

        crystals.clear();

        for (String id : config.getKeys(false)) {

            String name = config.getString(id + ".name");

            Map<CrystalStat, Double> stats = new HashMap<>();

            if (config.contains(id + ".stats")) {

                for (String statKey : config.getConfigurationSection(id + ".stats").getKeys(false)) {

                    CrystalStat stat = CrystalStat.valueOf(statKey.toUpperCase());

                    double value = config.getDouble(id + ".stats." + statKey);

                    stats.put(stat, value);

                }

            }

            Crystal crystal = new Crystal(id, name, stats);

            Crystals.put(id, crystal);

        }

    }

    public static Crystal getCrystal(String id) {

        return crystals.get(id);

    }

    public static Map<String, Crystal> getAll() {

        return crystals;

    }

} 
