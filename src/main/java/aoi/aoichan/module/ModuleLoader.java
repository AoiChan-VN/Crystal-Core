package aoi.aoichan.module;

import aoi.aoichan.AoiMain;

import java.util.ArrayList;
import java.util.List;

/*
 Quản lý module của Engine
*/

public class ModuleLoader {

    private final AoiMain plugin;

    private final List<EngineModule> modules = new ArrayList<>();

    public ModuleLoader(AoiMain plugin) {
        this.plugin = plugin;
    }

    // 【!】Code: đăng ký module
    public void registerModule(EngineModule module) {

        modules.add(module);

        module.onLoad();
        module.onEnable();

        plugin.getEngineLogger().info("Module load: " + module.getName());
    }

    // 【!】Code: shutdown module
    public void shutdownModules() {

        for (EngineModule module : modules) {

            module.onDisable();

            plugin.getEngineLogger().info("Module unload: " + module.getName());
        }

        modules.clear();
    }
} 
