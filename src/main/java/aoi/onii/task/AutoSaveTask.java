package vn.aoi.onii.task;

import org.bukkit.scheduler.BukkitRunnable;
import vn.aoi.onii.data.PlayerRepository;
import vn.aoi.onii.manager.PlayerManager;

public class AutoSaveTask extends BukkitRunnable {

    @Override
    public void run() {
        PlayerManager.getAll().values().forEach(PlayerRepository::saveAsync);
    }
}
