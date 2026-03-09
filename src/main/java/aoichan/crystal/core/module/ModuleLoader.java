package aoichan.crystal.core.module;

import aoichan.crystal.gameplay.gem.GemModule;
import java.util.ArrayList;
import java.util.List;

public class ModuleLoader {

    // [!] Code: Module Registry
    private final List<CrystalModule> modules = new ArrayList<>();

    public void register(CrystalModule module) {

        modules.add(module);
        module.enable();
    }

    public void loadModules() {

        // [!] Code: Register Gem Module
        register(new GemModule());

    }

    public void shutdown() {

        for (CrystalModule module : modules) {
            module.disable();
        }

        modules.clear();
    }
} 
