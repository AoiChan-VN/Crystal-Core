package aoichan.engine;

import aoichan.service.ServiceManager;
import aoichan.thread.AsyncPool;
import org.bukkit.plugin.java.JavaPlugin;

public class Engine {

    private static JavaPlugin plugin;
    private static ServiceManager serviceManager;

    public static void start(JavaPlugin pl) {
        plugin = pl;

        AsyncPool.init();

        serviceManager = new ServiceManager();
        serviceManager.init();
    }

    public static void shutdown() {
        if (serviceManager != null) {
            serviceManager.shutdown();
        }
        AsyncPool.shutdown();
    }

    public static JavaPlugin plugin() {
        return plugin;
    }

    public static ServiceManager services() {
        return serviceManager;
    }
} 
