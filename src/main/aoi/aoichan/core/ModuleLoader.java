package aoi.aoichan.core;

import aoi.aoichan.api.Module;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class ModuleLoader {

    // 【!】Code: danh sách module
    private final List<Module> modules = new ArrayList<>();

    private final JavaPlugin plugin;

    public ModuleLoader(JavaPlugin plugin){
        this.plugin = plugin;
    }

    // 【!】Code: register module
    public void registerModule(Module module){

        modules.add(module);
        module.enable();

        plugin.getLogger().info("Module enabled: " + module.getName());

    }

    // 【!】Code: disable module
    public void disableModules(){

        for(Module module : modules){
            module.disable();
        }

        modules.clear();

    }

}
