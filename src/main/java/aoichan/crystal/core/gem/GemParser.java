package aoichan.crystal.core.gem;

import aoichan.crystal.gameplay.element.ElementType;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

// [!] Code: Parse gems.yml
public class GemParser {

    public static void load(FileConfiguration config) {

        GemRegistry.clear();

        for (String id : config.getKeys(false)) {

            ConfigurationSection section =
                    config.getConfigurationSection(id);

            if (section == null)
                continue;

            String name =
                    section.getString("name");

            ElementType element =
                    ElementType.fromString(
                            section.getString("element")
                    );

            int maxLevel =
                    section.getInt("max-level", 1);

            Map<String, Double> stats =
                    new HashMap<>();

            ConfigurationSection statSec =
                    section.getConfigurationSection("stats");

            if (statSec != null) {

                for (String stat : statSec.getKeys(false)) {

                    stats.put(
                            stat,
                            statSec.getDouble(stat)
                    );
                }
            }

            List<String> effects =
                    section.getStringList("effects");

            Gem gem = new Gem(

                    id,
                    name,
                    element,
                    maxLevel,
                    stats,
                    effects

            );

            GemRegistry.register(gem);
        }
    }
} 
