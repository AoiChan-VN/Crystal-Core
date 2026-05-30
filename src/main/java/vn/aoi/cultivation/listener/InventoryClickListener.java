package vn.aoi.cultivation.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import vn.aoi.cultivation.config.ShopConfig.ShopItem;
import vn.aoi.cultivation.core.security.ClickCooldownManager;
import vn.aoi.cultivation.core.security.TransactionGuard;
import vn.aoi.cultivation.gui.holder.CustomInventoryHolder;
import vn.aoi.cultivation.gui.menu.ShopMenu;
import vn.aoi.cultivation.service.ShopItemFactory;
import vn.aoi.cultivation.service.ShopService;

public final class InventoryClickListener implements Listener {

    private final ClickCooldownManager clickCooldownManager;
    private final TransactionGuard transactionGuard;

    private final ShopService shopService;
    private final ShopItemFactory shopItemFactory;

    private final ShopMenu shopMenu;

    public InventoryClickListener(
            ClickCooldownManager clickCooldownManager,
            TransactionGuard transactionGuard,
            ShopService shopService,
            ShopItemFactory shopItemFactory,
            ShopMenu shopMenu
    ) {
        this.clickCooldownManager = clickCooldownManager;
        this.transactionGuard = transactionGuard;
        this.shopService = shopService;
        this.shopItemFactory = shopItemFactory;
        this.shopMenu = shopMenu;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {

        event.setCancelled(true);

        if (!(event.getWhoClicked() instanceof Player player)) {
            return;
        }

        Inventory clickedInventory =
                event.getClickedInventory();

        if (clickedInventory == null) {
            return;
        }

        ItemStack clickedItem =
                event.getCurrentItem();

        if (clickedItem == null) {
            return;
        }

        if (!(clickedInventory.getHolder()
                instanceof CustomInventoryHolder holder)) {
            return;
        }

        if (!clickCooldownManager.allowClick(
                player.getUniqueId()
        )) {
            return;
        }

        String guiId =
                holder.getGuiId();

        switch (guiId) {

            case "main_menu" -> handleMainMenuClick(
                    player,
                    clickedItem
            );

            case ShopMenu.GUI_ID -> handleShopMenuClick(
                    player,
                    clickedItem
            );

            default -> {
            }
        }
    }

    private void handleMainMenuClick(
            Player player,
            ItemStack clickedItem
    ) {

        String shopId =
                shopItemFactory.getShopId(
                        clickedItem
                );

        if (shopId != null) {
            return;
        }

        if (clickedItem.getType().name()
                .equals("EMERALD")) {

            shopMenu.open(player);
        }
    }

    private void handleShopMenuClick(
            Player player,
            ItemStack clickedItem
    ) {

        if (clickedItem.getType().name()
                .equals("BARRIER")) {

            player.closeInventory();
            return;
        }

        String shopId =
                shopItemFactory.getShopId(
                        clickedItem
                );

        if (shopId == null) {
            return;
        }

        ShopItem shopItem =
                shopService.getShopItem(
                        shopId
                );

        if (shopItem == null) {
            return;
        }

        transactionGuard.runAtomic(
                player,
                () -> purchaseItem(
                        player,
                        shopItem
                )
        );
    }

    private void purchaseItem(
            Player player,
            ShopItem shopItem
    ) {

        ItemStack reward =
                new ItemStack(
                        shopItem.getMaterial(),
                        shopItem.getAmount()
                );

        player.getInventory().addItem(
                reward
        );
    }
}
