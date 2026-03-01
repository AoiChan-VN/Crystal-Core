package aoichan.crystal.gui;

import aoichan.crystal.AoiMain;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

public class GemsGUI {

    public static Inventory create(AoiMain plugin) {
        Inventory inv = Bukkit.createInventory(null, 27, "§8Gems Ultimate");
        for (String id : plugin.getAPI().getAllGems()) {
            inv.addItem(plugin.getAPI().createGemItem(id));
        }
        return inv;
    }
}
