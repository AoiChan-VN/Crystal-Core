package vn.aoi.onii.task;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import vn.aoi.onii.manager.PlayerManager;
import vn.aoi.onii.data.PlayerRepository;

public class AutoSaveTask extends BukkitRunnable {

    @Override
    public void run() {
        PlayerManager.getAll().values().forEach(PlayerRepository::saveAsync);
    }
} 
