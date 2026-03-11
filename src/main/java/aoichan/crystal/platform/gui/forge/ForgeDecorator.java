package aoichan.crystal.platform.gui.forge;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

// [!] Code: Forge GUI decorator
public class ForgeDecorator {

    public static void decorate(Inventory inv) {

        ItemStack glass =
                new ItemStack(
                        Material.GRAY_STAINED_GLASS_PANE
                );

        for (int i = 0; i < 45; i++) {

            if (i == ForgeGUI.ITEM_SLOT) continue;
            if (i == ForgeGUI.GEM_SLOT) continue;
            if (i == ForgeGUI.FORGE_BUTTON) continue;

            inv.setItem(i, glass);
        }
    }

} 
