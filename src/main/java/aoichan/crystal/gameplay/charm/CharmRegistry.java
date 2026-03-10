package aoichan.crystal.gameplay.charm;

import aoichan.crystal.bootstrap.CrystalPlugin;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class CharmRegistry {

    private static final Map<String, CharmData> charms =
            new HashMap<>();

    // [!] Code: Load charm config
    public static void load() {

        charms.clear();

        File file = new File(
                CrystalPlugin.get().getDataFolder(),
                "charms.yml"
        );

        YamlConfiguration config =
                YamlConfiguration.loadConfiguration(file);

        ConfigurationSection section =
                config.getConfigurationSection("charms");

        if (section == null) return;

        for (String id : section.getKeys(false)) {

            ConfigurationSection charm =
                    section.getConfigurationSection(id);

            double bonus =
                    charm.getDouble("success-bonus");

            boolean protect =
                    charm.getBoolean("protect-item");

            charms.put(
                    id,
                    new CharmData(id, bonus, protect)
            );
        }
    }

    public static CharmData get(String id) {
        return charms.get(id);
    }
} 
