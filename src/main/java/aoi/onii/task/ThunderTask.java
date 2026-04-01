package vn.aoi.onii.task;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import vn.aoi.onii.Main;

import java.util.Random;

public class ThunderTask extends BukkitRunnable {

    private final Player player;
    private int time = 30;
    private final Random random = new Random();

    public ThunderTask(Player player) {
        this.player = player;
    }

    @Override
    public void run() {
        if (time-- <= 0) {
            cancel();
            return;
        }

        Location loc = player.getLocation().clone().add(
                random.nextInt(10) - 5,
                0,
                random.nextInt(10) - 5
        );

        player.getWorld().strikeLightning(loc);
    }

    public void start() {
        runTaskTimer(Main.getInstance(), 0, 20);
    }
} 
