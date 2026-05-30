package vn.aoi.cultivation.gui.menu;

import vn.aoi.cultivation.core.security.AntiDupeManager;
import vn.aoi.cultivation.gui.holder.CustomInventoryHolder;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ShopMenu {

    public static final String GUI_ID = "shop_menu";

    private static final int INVENTORY_SIZE = 54;

    private final AntiDupeManager antiDupeManager;

    public ShopMenu(AntiDupeManager antiDupeManager) {
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

        populateInventory(inventory);

        antiDupeManager.startSession(
                player,
                inventory
        );

        player.openInventory(inventory);
    }

    private void populateInventory(Inventory inventory) {

        fillBorders(inventory);

        inventory.setItem(
                20,
                createShopItem(
                        Material.EMERALD,
                        "§aLinh Thạch Hạ Phẩm",
                        List.of(
                                "§7Giá mua: 100",
                                "§7Giá bán: 75"
                        )
                )
        );

        inventory.setItem(
                22,
                createShopItem(
                        Material.DIAMOND,
                        "§bLinh Thạch Trung Phẩm",
                        List.of(
                                "§7Giá mua: 1000",
                                "§7Giá bán: 750"
                        )
                )
        );

        inventory.setItem(
                24,
                createShopItem(
                        Material.NETHER_STAR,
                        "§dLinh Thạch Thượng Phẩm",
                        List.of(
                                "§7Giá mua: 5000",
                                "§7Giá bán: 4000"
                        )
                )
        );

        inventory.setItem(
                49,
                createShopItem(
                        Material.BARRIER,
                        "§cĐóng Menu",
                        List.of(
                                "§7Nhấn để đóng giao diện"
                        )
                )
        );
    }

    private void fillBorders(Inventory inventory) {

        ItemStack filler =
                createShopItem(
                        Material.BLACK_STAINED_GLASS_PANE,
                        " ",
                        List.of()
                );

        for (int slot = 0; slot < inventory.getSize(); slot++) {

            if (isBorder(slot)) {
                inventory.setItem(slot, filler);
            }
        }
    }

    private boolean isBorder(int slot) {

        return slot < 9
                || slot >= 45
                || slot % 9 == 0
                || slot % 9 == 8;
    }

    private ItemStack createShopItem(Material material,
                                     String displayName,
                                     List<String> loreLines) {

        ItemStack item =
                new ItemStack(material);

        ItemMeta meta =
                item.getItemMeta();

        if (meta == null) {
            return item;
        }

        meta.setDisplayName(displayName);

        meta.setLore(
                new ArrayList<>(loreLines)
        );

        meta.addItemFlags(
                ItemFlag.HIDE_ATTRIBUTES
        );

        item.setItemMeta(meta);

        return item;
    }
} 
