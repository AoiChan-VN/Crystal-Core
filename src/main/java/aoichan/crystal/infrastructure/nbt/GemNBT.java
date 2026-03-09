package aoichan.crystal.infrastructure.nbt;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import aoichan.crystal.bootstrap.CrystalPlugin;

public class GemNBT {

    // [!] Code: Gem ID tag
    private static final NamespacedKey GEM_ID =
            new NamespacedKey(CrystalPlugin.get(), "gem_id");

    // [!] Code: Set gem id
    public static ItemStack tag(ItemStack item, String gemId) {

        ItemMeta meta = item.getItemMeta();

        meta.getPersistentDataContainer().set(
                GEM_ID,
                PersistentDataType.STRING,
                gemId
        );

        item.setItemMeta(meta);

        return item;
    }

    // [!] Code: Get gem id
    public static String get(ItemStack item) {

        if (item == null) return null;
        if (!item.hasItemMeta()) return null;

        ItemMeta meta = item.getItemMeta();

        return meta.getPersistentDataContainer().get(
                GEM_ID,
                PersistentDataType.STRING
        );
    }

    // [!] Code: Check if item is gem
    public static boolean isGem(ItemStack item) {

        return get(item) != null;
    }
}
