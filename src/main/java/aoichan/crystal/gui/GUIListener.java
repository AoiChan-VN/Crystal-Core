package aoichan.crystal.gui;

import aoichan.crystal.AoiMain;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

public class GUIListener implements Listener {

    private final AoiMain plugin;

    public GUIListener(AoiMain plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {

        InventoryView view = e.getView();

        if (view.getTitle().equalsIgnoreCase("§8Gems Ultimate")) {

            e.setCancelled(true);

            ItemStack clicked = e.getCurrentItem();
            if (clicked == null) return;

            // Future: handle gem selection logic
        }
    }
}
