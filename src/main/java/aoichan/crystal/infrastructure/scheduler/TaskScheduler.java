package aoichan.crystal.infrastructure.scheduler;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class TaskScheduler {

    private final JavaPlugin plugin;

    public TaskScheduler(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    // [!] Code: Run async task
    public void async(Runnable task) {

        Bukkit.getScheduler().runTaskAsynchronously(plugin, task);
    }

    // [!] Code: Run sync task
    public void sync(Runnable task) {

        Bukkit.getScheduler().runTask(plugin, task);
    }

    // [!] Code: Run delayed task
    public void later(Runnable task, long ticks) {

        Bukkit.getScheduler().runTaskLater(plugin, task, ticks);
    }
}
