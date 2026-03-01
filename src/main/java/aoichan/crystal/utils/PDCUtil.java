package aoichan.crystal.utils;

import aoichan.crystal.AoiMain;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.*;

public class PDCUtil {

    private static final NamespacedKey KEY =
            new NamespacedKey(AoiMain.get(), "gems");

    public static List<String> getSocketList(ItemStack item) {

        if (!item.hasItemMeta()) return new ArrayList<>();

        ItemMeta meta = item.getItemMeta();
        String raw = meta.getPersistentDataContainer()
                .get(KEY, PersistentDataType.STRING);

        if (raw == null) return new ArrayList<>();

        return new ArrayList<>(Arrays.asList(raw.split(",")));
    }

    public static void setSocketList(ItemStack item, List<String> list) {

        ItemMeta meta = item.getItemMeta();
        meta.getPersistentDataContainer()
                .set(KEY, PersistentDataType.STRING,
                        String.join(",", list));

        item.setItemMeta(meta);
    }

    public static boolean hasGemTag(ItemStack item) {
        return !getSocketList(item).isEmpty();
    }
}
