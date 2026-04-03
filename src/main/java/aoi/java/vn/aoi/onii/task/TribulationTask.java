package vn.aoi.onii.task;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import vn.aoi.onii.manager.PlayerManager;
import vn.aoi.onii.model.Cultivator;

import java.util.Random;

public class TribulationTask extends BukkitRunnable {

    private final Player player;
    private final PlayerManager playerManager;
    private final int duration;

    private int time = 0;
    private final Random random = new Random();

    public TribulationTask(Player player, PlayerManager playerManager, int duration) {
        this.player = player;
        this.playerManager = playerManager;
        this.duration = duration;
    }

    @Override
    public void run() {
        if (!player.isOnline() || player.isDead()) {
            fail();
            cancel();
            return;
        }

        if (time >= duration) {
            success();
            cancel();
            return;
        }

        Location loc = player.getLocation().clone().add(
                random.nextInt(6) - 3,
                0,
                random.nextInt(6) - 3
        );

        player.getWorld().strikeLightning(loc);

        time++;
    }

    private void fail() {
        Cultivator c = playerManager.get(player.getUniqueId());
        if (c != null) {
            c.setExp(0);
        }
    }

    private void success() {
        Cultivator c = playerManager.get(player.getUniqueId());
        if (c != null) {
            // TODO: rank up
        }
    }
} 
