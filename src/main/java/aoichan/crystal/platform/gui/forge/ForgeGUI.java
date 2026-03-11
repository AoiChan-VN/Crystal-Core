package aoichan.crystal.platform.gui.forge;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.entity.Player;

// [!] Code: Forge GUI builder
public class ForgeGUI {

    public static final int ITEM_SLOT = 20;
    public static final int GEM_SLOT = 24;
    public static final int FORGE_BUTTON = 22;

    public static void open(Player player) {

        Inventory inv =
                Bukkit.createInventory(
                        null,
                        45,
                        "Luyện Khí Phường"
                );

        ItemStack forge =
                new ItemStack(Material.ANVIL);

        inv.setItem(FORGE_BUTTON, forge);

        ForgeDecorator.decorate(inv);

        player.openInventory(inv);
    }

}
