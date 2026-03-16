package aoi.aoichan.engine.core;

import aoi.aoichan.engine.module.ModuleManager;
import aoi.aoichan.engine.registry.RegistryManager;
import aoi.aoichan.engine.scheduler.EngineScheduler;
import aoi.aoichan.engine.service.ServiceManager;
import aoi.aoichan.engine.util.EngineLogger;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Engine
 *
 * Core runtime container của toàn bộ MMORPG engine.
 *
 * Nơi giữ:
 * - ServiceManager
 * - ModuleManager
 * - RegistryManager
 * - Scheduler
 *
 * Tất cả plugin khác sẽ hook vào đây.
 */
public class Engine {

    private final JavaPlugin plugin;

    private final ServiceManager serviceManager;
    private final ModuleManager moduleManager;
    private final RegistryManager registryManager;
    private final EngineScheduler scheduler;

    public Engine(JavaPlugin plugin) {

        this.plugin = plugin;

        this.serviceManager = new ServiceManager();
        this.moduleManager = new ModuleManager();
        this.registryManager = new RegistryManager();
        this.scheduler = new EngineScheduler(plugin);

    }

    public void start() {

        EngineLogger.info("Starting Engine Systems...");

        serviceManager.initialize();
        registryManager.initialize();
        moduleManager.initialize();
        scheduler.initialize();

        EngineLogger.info("Engine Systems started.");
    }

    public void shutdown() {

        EngineLogger.info("Stopping Engine Systems...");

        moduleManager.shutdown();
        serviceManager.shutdown();
        scheduler.shutdown();

        EngineLogger.info("Engine Systems stopped.");
    }

    public JavaPlugin getPlugin() {
        return plugin;
    }

    public ServiceManager getServiceManager() {
        return serviceManager;
    }

    public ModuleManager getModuleManager() {
        return moduleManager;
    }

    public RegistryManager getRegistryManager() {
        return registryManager;
    }

    public EngineScheduler getScheduler() {
        return scheduler;
    }
} 
