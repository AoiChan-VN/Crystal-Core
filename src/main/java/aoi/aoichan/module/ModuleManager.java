package aoi.aoichan.module;

import aoi.aoichan.service.ServiceRegistry;

import java.util.ArrayList;
import java.util.List;

/**
 * Load và quản lý modules (AoiStats, AoiClass, ...)
 */
public class ModuleManager {

    private final ServiceRegistry registry;
    private final List<EngineModule> modules = new ArrayList<>();

    public ModuleManager(ServiceRegistry registry) {
        this.registry = registry;
    }

    public void loadModules() {
        // TODO: Sau này scan plugin folder
        // Hiện tại register manual core module

        // Example:
        // modules.add(new StatsModule());

        for (EngineModule module : modules) {
            module.onLoad(registry);
        }
    }

    public void unloadModules() {
        for (EngineModule module : modules) {
            module.onUnload();
        }
    }

    public void registerModule(EngineModule module) {
        modules.add(module);
    }
} 
