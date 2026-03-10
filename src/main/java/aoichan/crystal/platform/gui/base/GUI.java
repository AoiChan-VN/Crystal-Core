package aoichan.crystal.platform.gui.base;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public abstract class GUI {

    // [!] Code: Open GUI
    public abstract void open(Player player);

    // [!] Code: Inventory
    public abstract Inventory create();
}
