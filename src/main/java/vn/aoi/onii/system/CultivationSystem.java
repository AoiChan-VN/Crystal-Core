package vn.aoi.onii.system;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;
import vn.aoi.onii.Main;
import vn.aoi.onii.player.PlayerData;
import org.bukkit.configuration.ConfigurationSection;

import java.util.Random;

public class CultivationSystem implements Listener {

    private final Main plugin;
    private final Random random = new Random();

    public CultivationSystem(Main plugin) {
        this.plugin = plugin;
    }

    public void tryBreakthrough(Player player, PlayerData data) {
        ConfigurationSection rank = plugin.getRankManager().getRank(data.getRank());
        if (rank == null) return;

        int level = data.getLevel();

        if (rank.contains("levels")) {
            ConfigurationSection levelSec = rank.getConfigurationSection("levels." + level);
            if (levelSec == null) return;

            int required = levelSec.getInt("exp");

            if (data.getExp() < required) {
                player.sendMessage("§cKhông đủ linh khí!");
                return;
            }

            data.setLevel(level + 1);
            data.addExp(-required);

            player.sendMessage("§aĐột phá thành công!");

        } else if (rank.getBoolean("is-do-kiep")) {
            startLightningTrial(player, data);
        }
    }

    private void startLightningTrial(Player player, PlayerData data) {
        player.sendMessage("§eBắt đầu Độ Kiếp...");

        new BukkitRunnable() {
            int ticks = 0;

            @Override
            public void run() {
                if (ticks >= 600 || player.isDead()) {
                    if (!player.isDead()) {
                        player.sendMessage("§aĐộ kiếp thành công!");
                        data.setRank("Chân tiên");
                    }
                    cancel();
                    return;
                }

                strikeRandom(player.getLocation());
                ticks += 20;
            }
        }.runTaskTimer(plugin, 0, 20);
    }

    private void strikeRandom(Location loc) {
        World w = loc.getWorld();
        double x = loc.getX() + (random.nextInt(10) - 5);
        double z = loc.getZ() + (random.nextInt(10) - 5);
        w.strikeLightning(new Location(w, x, loc.getY(), z));
    }
} 
