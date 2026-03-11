package aoichan.crystal.platform.gui.base;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

// [!] Code: GUI border decorator
public class GUIBorder {

    public static void fill(Inventory inv) {

        ItemStack glass =
                new ItemStack(
                        Material.GRAY_STAINED_GLASS_PANE
                );

        for (int i = 0; i < inv.getSize(); i++) {

            if (inv.getItem(i) == null)
                inv.setItem(i, glass);
        }

    }

} 
