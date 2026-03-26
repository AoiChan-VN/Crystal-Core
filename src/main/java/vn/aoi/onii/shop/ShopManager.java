package vn.aoi.onii.shop;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import vn.aoi.onii.Main;

import java.io.File;
import java.util.*;

public class ShopManager {

    private YamlConfiguration config;

    public ShopManager() {
        File file = Main.getInstance().getShopFile();
        config = YamlConfiguration.loadConfiguration(file);
    }

    public Inventory createShop() {
        String title = config.getString("shop.title");
        Inventory inv = Bukkit.createInventory(null, 27, title);

        for (String key : config.getConfigurationSection("shop.items").getKeys(false)) {

            String path = "shop.items." + key;

            Material mat = Material.valueOf(config.getString(path + ".material"));
            String name = config.getString(path + ".name");
            double price = config.getDouble(path + ".price");
            String tier = config.getString(path + ".tier");

            ItemStack item = new ItemStack(mat);
            ItemMeta meta = item.getItemMeta();

            meta.setDisplayName(name);

            meta.setLore(Arrays.asList(
                    "§7Cấp: §e" + tier,
                    "§7Giá: §a" + price,
                    "§8Click để mua"
            ));

            item.setItemMeta(meta);
            inv.addItem(item);
        }

        return inv;
    }

    public YamlConfiguration getConfig() {
        return config;
    }
}
