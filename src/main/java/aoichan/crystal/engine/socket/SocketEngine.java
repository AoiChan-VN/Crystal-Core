package aoichan.crystal.engine.socket;

import aoichan.crystal.infrastructure.nbt.GemNBT;
import aoichan.crystal.infrastructure.nbt.ItemGemNBT;
import org.bukkit.inventory.ItemStack;

public class SocketEngine {

    // [!] Code: Apply gem to item
    public boolean apply(ItemStack item, ItemStack gem) {

        String gemId = GemNBT.get(gem);

        if (gemId == null) {
            return false;
        }

        ItemGemNBT.addGem(item, gemId);

        return true;
    }
} 
