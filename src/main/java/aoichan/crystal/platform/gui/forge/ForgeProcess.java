package aoichan.crystal.platform.gui.forge;

import aoichan.crystal.bootstrap.CrystalPlugin;
import aoichan.crystal.core.forge.ForgeEngine;
import aoichan.crystal.core.item.GemItemUtil;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;
import java.util.Set;

// [!] Code: Forge ritual process
public class ForgeProcess {

    private static final Set<Player> LOCKED =
            new HashSet<>();

    public static boolean isLocked(Player p) {
        return LOCKED.contains(p);
    }

    public static void start(Player player, Inventory inv) {

        if (LOCKED.contains(player))
            return;

        LOCKED.add(player);

        ItemStack item =
                inv.getItem(ForgeGUI.ITEM_SLOT);

        ItemStack gem =
                inv.getItem(ForgeGUI.GEM_SLOT);

        player.sendMessage("§7【Luyện khí sư】: đang rèn...");

        Bukkit.getScheduler().runTaskLater(

                CrystalPlugin.get(),

                () -> {

                    boolean success =
                            ForgeEngine.forge(item, gem);

                    inv.setItem(ForgeGUI.GEM_SLOT, null);

                    String gemId =
                            GemItemUtil.getGemId(gem);

                    int level =
                            GemItemUtil.getGemLevel(gem);

                    if (success) {

                        player.playSound(
                                player.getLocation(),
                                Sound.BLOCK_NOTE_BLOCK_BELL,
                                1f,
                                1.2f
                        );

                        Bukkit.broadcastMessage(
                                "§6【Thiên Đạo】: "
                                + player.getName()
                                + " độ kiếp cho "
                                + gemId
                                + " Lv."
                                + level
                                + " thành công."
                        );

                    } else {

                        player.playSound(
                                player.getLocation(),
                                Sound.BLOCK_ANVIL_BREAK,
                                1f,
                                1f
                        );

                        player.sendMessage(
                                "§cLuyện khí thất bại..."
                        );

                    }

                    LOCKED.remove(player);

                },

                100L // 5 seconds

        );
    }

} 
