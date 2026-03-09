package aiochan.crystal.core.module;

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

        // [!] Code: Future modules will register here

    }

    public void shutdown() {

        for (CrystalModule module : modules) {
            module.disable();
        }

        modules.clear();
    }
} 
