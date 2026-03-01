package aoichan.crystal.core;

import aoichan.crystal.utils.PDCUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.inventory.ItemStack;

/**
 * Anti-duplication protection:
 * - Block shift-click or collect-to-cursor when item has gem PDC tags.
 * - Uses PDCUtil.hasGemTag(...) (safe, checks initialization).
 */
public class AntiDupeManager implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onInventoryClick(InventoryClickEvent e) {

        ItemStack current = e.getCurrentItem();
        if (current == null) return;

        // If item has gem PDC tag, block certain actions
        boolean hasGem = false;
        try {
            hasGem = PDCUtil.hasGemTag(current);
        } catch (IllegalStateException ex) {
            // PDCUtil not initialized — safest: do nothing (do not crash)
            e.getView().getPlayer().sendMessage("§c[Warning] PDCUtil not initialized; anti-dupe limited.");
            return;
        }

        if (!hasGem) return;

        // Block shift or collect-to-cursor actions that may duplicate items
        if (e.getClick().isShiftClick() || e.getAction() == InventoryAction.COLLECT_TO_CURSOR) {
            e.setCancelled(true);
        }
    }
}
