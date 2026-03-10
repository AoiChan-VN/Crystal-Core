package aoichan.crystal.gameplay.charm;

import aoichan.crystal.infrastructure.pdc.PDCKeys;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;

public class CharmItemBuilder {

    // [!] Code: Create charm item
    public static ItemStack build(String id) {

        CharmData data =
                CharmRegistry.get(id);

        if (data == null) return null;

        ItemStack item =
                new ItemStack(Material.NETHER_STAR);

        ItemMeta meta =
                item.getItemMeta();

        meta.setDisplayName("§6Charm: " + id);

        meta.setLore(List.of(
                "§7Success Bonus: §a+" +
                        data.getSuccessBonus() + "%",
                data.isProtectItem()
                        ? "§aProtect item on fail"
                        : "§cItem can break"
        ));

        // [!] Code: Save charm id
        meta.getPersistentDataContainer().set(
                PDCKeys.CHARM_ID,
                PersistentDataType.STRING,
                id
        );

        item.setItemMeta(meta);

        return item;
    }
} 
