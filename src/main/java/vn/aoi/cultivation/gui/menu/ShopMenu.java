package vn.aoi.cultivation.gui.menu;

import vn.aoi.cultivation.config.ShopConfig.ShopItem;
import vn.aoi.cultivation.core.security.AntiDupeManager;
import vn.aoi.cultivation.gui.holder.CustomInventoryHolder;
import vn.aoi.cultivation.service.ShopService;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collection;
import java.util.List;

public final class ShopMenu {

    public static final String GUI_ID = "shop_menu";

    private static final int INVENTORY_SIZE = 54;
    private static final int FIRST_ITEM_SLOT = 10;

    private final ShopService shopService;
    private final AntiDupeManager antiDupeManager;

    public ShopMenu(ShopService shopService,
                    AntiDupeManager antiDupeManager) {

        this.shopService = shopService;
        this.antiDupeManager = antiDupeManager;
    }

    public void open(Player player) {

        CustomInventoryHolder holder =
                new CustomInventoryHolder(GUI_ID);

        Inventory inventory =
                Bukkit.createInventory(
                        holder,
                        INVENTORY_SIZE,
                        "Cultivation Shop"
                );

        holder.setInventory(inventory);

        buildInventory(inventory);

        antiDupeManager.startSession(
                player,
                inventory
        );

        player.openInventory(inventory);
    }

    private void buildInventory(Inventory inventory) {

        fillBorder(inventory);

        Collection<ShopItem> items =
                shopService.getShopItems();

        int slot = FIRST_ITEM_SLOT;

        for (ShopItem shopItem : items) {

            if (slot >= 44) {
                break;
            }

            if (isBorderSlot(slot)) {
                slot++;
            }

            inventory.setItem(
                    slot,
                    shopService.createDisplayItem(shopItem)
            );

            slot++;
        }

        inventory.setItem(
                49,
                createCloseButton()
        );
    }

    private void fillBorder(Inventory inventory) {

        ItemStack border =
                createBorderItem();

        for (int slot = 0;
             slot < inventory.getSize();
             slot++) {

            if (isBorderSlot(slot)) {

                inventory.setItem(
                        slot,
                        border
                );
            }
        }
    }

    private boolean isBorderSlot(int slot) {

        return slot < 9
                || slot >= 45
                || slot % 9 == 0
                || slot % 9 == 8;
    }

    private ItemStack createBorderItem() {

        ItemStack item =
                new ItemStack(
                        Material.BLACK_STAINED_GLASS_PANE
                );

        ItemMeta meta =
                item.getItemMeta();

        if (meta != null) {

            meta.setDisplayName(" ");

            meta.addItemFlags(
                    ItemFlag.HIDE_ATTRIBUTES
            );

            item.setItemMeta(meta);
        }

        return item;
    }

    private ItemStack createCloseButton() {

        ItemStack item =
                new ItemStack(
                        Material.BARRIER
                );

        ItemMeta meta =
                item.getItemMeta();

        if (meta != null) {

            meta.setDisplayName("§cĐóng");

            meta.setLore(
                    List.of(
                            "§7Nhấn để đóng giao diện"
                    )
            );

            item.setItemMeta(meta);
        }

        return item;
    }
} 
