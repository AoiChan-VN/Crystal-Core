package aoichan.crystal.core;

import aoichan.crystal.AoiMain;
import aoichan.crystal.utils.PDCUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class RefineManager {

    private final AoiMain plugin;
    private final RefineSoundManager soundManager;
    private final ProtectionManager protectionManager;
    private final Random random = new Random();

    public RefineManager(AoiMain plugin) {
        this.plugin = plugin;
        this.soundManager = new RefineSoundManager(plugin);
        this.protectionManager = new ProtectionManager(plugin);
    }

    public void refine(Player player,
                       ItemStack gem,
                       ItemStack item,
                       String quality,
                       int level,
                       double successRate) {

        int delay = plugin.getConfig().getInt("refine.delay-ticks", 20);

        Bukkit.getScheduler().runTaskLater(plugin, () -> {

            announce(player, "§6【Thiên Đạo】§eLôi kiếp sắp đến...");

            boolean success = random.nextDouble() <= successRate;

            if (success) {

                gem.setAmount(0);

                announce(player, "§aDung hợp thành công!");

                soundManager.playSuccess(player, quality, level);
                return;
            }

            gem.setAmount(0);

            double breakRate = plugin.getConfig()
                    .getDouble("qualities." + quality + ".item-break-rate", 0);

            boolean highTier = level >= 7;

            if (highTier && random.nextDouble() <= breakRate) {

                if (protectionManager.isPermanentProtected(item)) {

                    player.sendMessage("§bItem được bảo hộ vĩnh viễn.");
                    soundManager.playThunder(player);
                    return;
                }

                if (protectionManager.consumeProtection(item)) {

                    player.sendMessage("§bBùa bảo vệ đã kích hoạt.");
                    soundManager.playThunder(player);
                    return;
                }

                item.setAmount(0);

                Bukkit.broadcastMessage("§4Thần Binh của "
                        + player.getName()
                        + " đã bị phá hủy!");

                soundManager.playExplosion(player);
                return;
            }

            announce(player, "§cDung hợp thất bại.");

            soundManager.playFail(player, highTier);

        }, delay);
    }

    private void announce(Player player, String msg) {

        String mode = plugin.getConfig()
                .getString("refine.broadcast-mode", "SERVER");

        switch (mode.toUpperCase()) {
            case "SERVER" -> Bukkit.broadcastMessage(msg);
            case "PERSONAL" -> player.sendMessage(msg);
            default -> {}
        }
    }
  }
