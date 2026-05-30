package vn.aoi.cultivation.service;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import vn.aoi.cultivation.CultivationPlugin;
import vn.aoi.cultivation.config.MenuConfig;

import java.util.ArrayList;
import java.util.List;

public final class MenuItemFactory {

    public static final String ACTION_KEY =
            "menu_action";

    private final NamespacedKey actionKey;

    public MenuItemFactory(
            CultivationPlugin plugin
    ) {

        this.actionKey =
                new NamespacedKey(
                        plugin,
                        ACTION_KEY
                );
    }

    public ItemStack createItem(
            MenuConfig.MenuItemConfig config
    ) {

        Material material =
                config.getMaterial();

        ItemStack item =
                new ItemStack(
                        material,
                        Math.max(
                                1,
                                config.getAmount()
                        )
                );

        ItemMeta meta =
                item.getItemMeta();

        if (meta == null) {
            return item;
        }

        meta.setDisplayName(
                colorize(
                        config.getDisplayName()
                )
        );

        List<String> lore =
                new ArrayList<>();

        for (String line : config.getLore()) {

            lore.add(
                    colorize(line)
            );
        }

        meta.setLore(lore);

        meta.addItemFlags(
                ItemFlag.HIDE_ATTRIBUTES
        );

        PersistentDataContainer pdc =
                meta.getPersistentDataContainer();

        pdc.set(
                actionKey,
                PersistentDataType.STRING,
                config.getAction()
        );

        item.setItemMeta(meta);

        return item;
    }

    public ItemStack createFillItem(
            MenuConfig.FillConfig config
    ) {

        ItemStack item =
                new ItemStack(
                        config.getMaterial()
                );

        ItemMeta meta =
                item.getItemMeta();

        if (meta == null) {
            return item;
        }

        meta.setDisplayName(
                colorize(
                        config.getDisplayName()
                )
        );

        item.setItemMeta(meta);

        return item;
    }

    public boolean hasAction(
            ItemStack item
    ) {

        return getAction(item) != null;
    }

    public String getAction(
            ItemStack item
    ) {

        if (item == null) {
            return null;
        }

        if (!item.hasItemMeta()) {
            return null;
        }

        ItemMeta meta =
                item.getItemMeta();

        if (meta == null) {
            return null;
        }

        PersistentDataContainer pdc =
                meta.getPersistentDataContainer();

        if (!pdc.has(
                actionKey,
                PersistentDataType.STRING
        )) {
            return null;
        }

        return pdc.get(
                actionKey,
                PersistentDataType.STRING
        );
    }

    private String colorize(
            String text
    ) {

        return ChatColor.translateAlternateColorCodes(
                '&',
                text
        );
    }
} 
