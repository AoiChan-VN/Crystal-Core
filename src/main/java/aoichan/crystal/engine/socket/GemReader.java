package aoichan.crystal.engine.socket;

import aoichan.crystal.api.gem.GemDefinition;
import aoichan.crystal.core.registry.GemRegistry;
import aoichan.crystal.infrastructure.nbt.ItemGemNBT;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class GemReader {

    private final GemRegistry registry;

    public GemReader(GemRegistry registry) {
        this.registry = registry;
    }

    // [!] Code: Read gems from item
    public List<GemDefinition> read(ItemStack item) {

        List<String> gemIds = ItemGemNBT.getGems(item);

        List<GemDefinition> gems = new ArrayList<>();

        for (String id : gemIds) {

            GemDefinition gem = registry.get(id);

            if (gem != null) {
                gems.add(gem);
            }
        }

        return gems;
    }
} 
