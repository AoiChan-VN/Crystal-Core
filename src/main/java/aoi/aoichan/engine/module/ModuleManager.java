package aoi.aoichan.engine.module;

import aoi.aoichan.engine.util.EngineLogger;

import java.util.ArrayList;
import java.util.List;

/**
 * ModuleManager
 *
 * Module = hệ thống gameplay.
 *
 * Ví dụ:
 * - AoiStats
 * - AoiSkill
 * - AoiClass
 */
public class ModuleManager {

    private final List<Module> modules = new ArrayList<>();

    public void initialize() {
        EngineLogger.info("ModuleManager initialized.");
    }

    public void register(Module module) {

        modules.add(module);

        EngineLogger.info("Registered module: " + module.getName());

        module.enable();
    }

    public void shutdown() {

        for (Module module : modules) {

            try {
                module.disable();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        modules.clear();
    }
} 
