package vn.aoi.cultivation.service;

import vn.aoi.cultivation.config.ShopConfig;
import vn.aoi.cultivation.config.ShopConfig.ShopItem;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class ShopService {

    private final ShopConfig shopConfig;

    public ShopService(ShopConfig shopConfig) {
        this.shopConfig = shopConfig;
    }

    public Collection<ShopItem> getShopItems() {
        return shopConfig.getShopItems().values();
    }

    public ShopItem getShopItem(String id) {
        return shopConfig.getItem(id);
    }

    public ItemStack createDisplayItem(ShopItem shopItem) {

        Material material = shopItem.getMaterial();

        ItemStack item =
                new ItemStack(
                        material,
                        Math.max(1, shopItem.getAmount())
                );

        ItemMeta meta = item.getItemMeta();

        if (meta == null) {
            return item;
        }

        meta.setDisplayName(
                colorize(
                        shopItem.getDisplayName()
                )
        );

        List<String> lore = new ArrayList<>();

        lore.add("§7Giá mua: §a" + shopItem.getBuyPrice());
        lore.add("§7Giá bán: §c" + shopItem.getSellPrice());

        meta.setLore(lore);

        meta.addItemFlags(
                ItemFlag.HIDE_ATTRIBUTES
        );

        item.setItemMeta(meta);

        return item;
    }

    public ItemStack createRewardItem(ShopItem shopItem) {

        return new ItemStack(
                shopItem.getMaterial(),
                Math.max(1, shopItem.getAmount())
        );
    }

    public boolean containsItem(String id) {
        return shopConfig.contains(id);
    }

    private String colorize(String text) {

        return ChatColor.translateAlternateColorCodes(
                '&',
                text
        );
    }
} 
