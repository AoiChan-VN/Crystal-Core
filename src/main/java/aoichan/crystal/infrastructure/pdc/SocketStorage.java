package aoichan.crystal.infrastructure.pdc;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class SocketStorage {

    // [!] Code: Get sockets
    public static int getSockets(ItemStack item) {

        ItemMeta meta = item.getItemMeta();

        if (meta == null) return 0;

        Integer value =
                meta.getPersistentDataContainer()
                        .get(PDCKeys.SOCKETS, PersistentDataType.INTEGER);

        return value == null ? 0 : value;
    }

    // [!] Code: Set sockets
    public static void setSockets(ItemStack item, int amount) {

        ItemMeta meta = item.getItemMeta();

        meta.getPersistentDataContainer()
                .set(PDCKeys.SOCKETS,
                        PersistentDataType.INTEGER,
                        amount);

        item.setItemMeta(meta);
    }
} 
