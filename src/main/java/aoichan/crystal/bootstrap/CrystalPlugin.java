package aoichan.crystal.bootstrap;

import aoichan.crystal.core.module.ModuleLoader;
import aoichan.crystal.infrastructure.config.ConfigLoader;
import aoichan.crystal.infrastructure.scheduler.TaskScheduler;
import org.bukkit.plugin.java.JavaPlugin;

public class CrystalPlugin extends JavaPlugin {

    private static CrystalPlugin instance;

    private ModuleLoader moduleLoader;
    private ConfigLoader configLoader;
    private TaskScheduler scheduler;

    @Override
    public void onEnable() {

        instance = this;

        // [!] Code: Plugin Startup
        getLogger().info("Crystal Ultimate is awakening...");

        // [!] Code: Load Config System
        this.configLoader = new ConfigLoader(this);
        configLoader.load();

        // [!] Code: Scheduler System
        this.scheduler = new TaskScheduler(this);

        // [!] Code: Module Loader
        this.moduleLoader = new ModuleLoader();
        moduleLoader.loadModules();

        getLogger().info("Crystal Ultimate loaded successfully.");
    }

    @Override
    public void onDisable() {

        // [!] Code: Plugin Shutdown
        getLogger().info("Crystal Ultimate shutting down...");

        if (moduleLoader != null) {
            moduleLoader.shutdown();
        }
    }

    public static CrystalPlugin get() {
        return instance;
    }

    public ModuleLoader getModuleLoader() {
        return moduleLoader;
    }

    public ConfigLoader getConfigLoader() {
        return configLoader;
    }

    public TaskScheduler getScheduler() {
        return scheduler;
    }
}
