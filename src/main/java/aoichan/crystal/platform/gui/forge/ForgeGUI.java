package aoichan.crystal.platform.gui.forge;

import aoichan.crystal.platform.gui.base.GUI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class ForgeGUI extends GUI {

    // [!] Code: GUI slots
    public static final int ITEM_SLOT = 11;
    public static final int GEM_SLOT = 15;
    public static final int BUTTON_SLOT = 13;
    public static final int INFO_SLOT = 22;

    @Override
    public void open(Player player) {
        player.openInventory(create());
    }

    @Override
    public Inventory create() {

        Inventory inv = Bukkit.createInventory(
                null,
                27,
                "§5Crystal Forge"
        );

        return inv;
    }
}
