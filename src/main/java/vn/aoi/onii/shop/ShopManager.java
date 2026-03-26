package vn.aoi.onii.shop;

import org.bukkit.*;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import vn.aoi.onii.Main;

import java.io.File;
import java.util.*;

public class ShopManager {

    private final YamlConfiguration config;

    public ShopManager() {
        config = YamlConfiguration.loadConfiguration(Main.getInstance().getShopFile());
    }

    public Inventory createShop(int page) {

        List<String> keys = new ArrayList<>(config.getConfigurationSection("shop.items").getKeys(false));

        Inventory inv = Bukkit.createInventory(null, 54,
                config.getString("shop.title") + " §7[" + page + "]");

        int start = (page - 1) * 28;
        int end = Math.min(start + 28, keys.size());

        int slot = 10;

        for (int i = start; i < end; i++) {

            String path = "shop.items." + keys.get(i);

            ItemStack item = new ItemStack(Material.valueOf(config.getString(path + ".material")));
            ItemMeta meta = item.getItemMeta();

            meta.setDisplayName(config.getString(path + ".name"));
            meta.setLore(Arrays.asList(
                    "§7Cấp: §e" + config.getString(path + ".tier"),
                    "§7Giá: §a" + config.getDouble(path + ".price")
            ));

            item.setItemMeta(meta);
            inv.setItem(slot, item);

            slot++;
            if (slot % 9 == 8) slot += 2;
        }

        inv.setItem(45, createBtn("§e⬅"));
        inv.setItem(53, createBtn("§e➡"));

        return inv;
    }

    private ItemStack createBtn(String name) {
        ItemStack i = new ItemStack(Material.ARROW);
        ItemMeta m = i.getItemMeta();
        m.setDisplayName(name);
        i.setItemMeta(m);
        return i;
    }

    public YamlConfiguration getConfig() {
        return config;
    }
}
