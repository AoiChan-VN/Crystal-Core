package aoi.aoichan.module;

import java.util.ArrayList;
import java.util.List;

/*
 Quản lý module engine
*/

public class ModuleManager {

    private final List<EngineModule> modules = new ArrayList<>();

    public void register(EngineModule module) {

        modules.add(module);

        // 【!】Code: bật module
        module.enable();

    }

    public void shutdown() {

        for (EngineModule module : modules) {

            module.disable();

        }

        modules.clear();

    }

}
