package aoichan.crystal.gameplay.gem;

import aoichan.crystal.api.gem.GemDefinition;
import aoichan.crystal.infrastructure.nbt.GemNBT;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GemItemFactory {

    // [!] Code: Create gem item
    public static ItemStack create(GemDefinition gem) {

        ItemStack item = new ItemStack(Material.AMETHYST_SHARD);

        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(gem.getName());
        meta.setLore(gem.getLore());

        item.setItemMeta(meta);

        // [!] Code: Tag gem id
        GemNBT.tag(item, gem.getId());

        return item;
    }
} 
