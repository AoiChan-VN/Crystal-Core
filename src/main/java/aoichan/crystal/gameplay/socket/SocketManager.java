package aoichan.crystal.gameplay.socket;

import aoichan.crystal.engine.socket.SocketEngine;
import org.bukkit.inventory.ItemStack;

public class SocketManager {

    private final SocketEngine engine = new SocketEngine();

    // [!] Code: Socket gem
    public boolean socket(ItemStack item, ItemStack gem) {

        return engine.apply(item, gem);
    }
}
