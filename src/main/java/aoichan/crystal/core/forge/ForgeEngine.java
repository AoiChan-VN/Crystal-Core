package aoichan.crystal.core.forge;

import aoichan.crystal.core.item.GemItemUtil;
import aoichan.crystal.core.socket.SocketEngine;

import org.bukkit.inventory.ItemStack;

import java.util.Random;

// [!] Code: Forge core logic
public class ForgeEngine {

    private static final Random random =
            new Random();

    public static boolean forge(
            ItemStack item,
            ItemStack gem
    ) {

        if (!GemItemUtil.isGem(gem))
            return false;

        String gemId =
                GemItemUtil.getGemId(gem);

        int level =
                GemItemUtil.getGemLevel(gem);

        double chance = 0.75;

        if (random.nextDouble() > chance)
            return false;

        SocketEngine.addGem(
                item,
                gemId,
                level
        );

        return true;
    }

}
