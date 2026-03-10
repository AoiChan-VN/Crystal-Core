package aoichan.crystal.infrastructure.nbt;

import aoichan.crystal.bootstrap.CrystalPlugin;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.Arrays;
import java.util.List;

public class ItemGemNBT {

    // [!] Code: NBT key
    private static final NamespacedKey GEM_LIST =
            new NamespacedKey(CrystalPlugin.get(), "gems");

    // [!] Code: Get gems
    public static List<String> getGems(ItemStack item) {

        if (!item.hasItemMeta()) return List.of();

        ItemMeta meta = item.getItemMeta();

        String data = meta.getPersistentDataContainer().get(
                GEM_LIST,
                PersistentDataType.STRING
        );

        if (data == null || data.isEmpty()) {
            return List.of();
        }

        return Arrays.asList(data.split(","));
    }

    // [!] Code: Add gem
    public static void addGem(ItemStack item, String gemId) {

        ItemMeta meta = item.getItemMeta();

        List<String> gems = getGems(item);

        String newData;

        if (gems.isEmpty()) {

            newData = gemId;

        } else {

            newData = String.join(",", gems) + "," + gemId;
        }

        meta.getPersistentDataContainer().set(
                GEM_LIST,
                PersistentDataType.STRING,
                newData
        );

        item.setItemMeta(meta);
    }
} 
