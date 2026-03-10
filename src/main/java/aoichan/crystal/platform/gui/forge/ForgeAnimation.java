package aoichan.crystal.platform.gui.forge;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitRunnable;

public class ForgeAnimation {

    // [!] Code: Forge progress animation
    public static void start(Player player, Inventory inv) {

        new BukkitRunnable() {

            int tick = 0;

            @Override
            public void run() {

                tick++;

                player.playSound(
                        player.getLocation(),
                        Sound.BLOCK_ANVIL_USE,
                        1f,
                        1f
                );

                if (tick >= 20) {

                    cancel();
                }
            }

        }.runTaskTimer(
                Bukkit.getPluginManager()
                        .getPlugin("CrystalUltimate"),
                0,
                1
        );
    }
} 
