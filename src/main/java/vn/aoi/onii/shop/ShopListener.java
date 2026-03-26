package vn.aoi.onii.shop;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import vn.aoi.onii.Main;
import vn.aoi.onii.enums.TechniqueTier;

public class ShopListener implements Listener {

    private final Economy econ;

    public ShopListener(Economy econ) {
        this.econ = econ;
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (e.getView().getTitle().equals("Cửa Hàng Công Pháp")) {

            e.setCancelled(true);

            if (!(e.getWhoClicked() instanceof Player player)) return;

            ItemStack item = e.getCurrentItem();
            if (item == null || !item.hasItemMeta()) return;

            FileConfiguration shop = Main.getInstance().getConfig();

            for (String key : shop.getConfigurationSection("shop.items").getKeys(false)) {

                String path = "shop.items." + key;

                String name = shop.getString(path + ".name");

                if (!item.getItemMeta().getDisplayName().equals(name)) continue;

                double price = shop.getDouble(path + ".price");
                String tierName = shop.getString(path + ".tier");

                if (!econ.has(player, price)) {
                    player.sendMessage("§cKhông đủ linh thạch!");
                    return;
                }

                econ.withdrawPlayer(player, price);

                TechniqueTier tier = TechniqueTier.valueOf(tierName);

                player.sendMessage("§aBạn đã lĩnh ngộ công pháp cấp " + tier.name());

                return;
            }
        }
    }
} 
