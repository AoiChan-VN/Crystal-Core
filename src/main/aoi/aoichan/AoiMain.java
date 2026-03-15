package aoi.aoichan;

import aoi.aoichan.command.EngineCommand;
import aoi.aoichan.config.ConfigManager;
import aoi.aoichan.core.EngineCore;
import org.bukkit.plugin.java.JavaPlugin;

public final class AoiMain extends JavaPlugin {

    // 【!】Code: Instance singleton
    private static AoiMain instance;

    // 【!】Code: Engine core
    private EngineCore engineCore;

    public static AoiMain getInstance() {
        return instance;
    }

    public EngineCore getEngineCore() {
        return engineCore;
    }

    @Override
    public void onEnable() {

        // 【!】Code: lưu instance
        instance = this;

        // 【!】Code: load config
        ConfigManager.init(this);

        // 【!】Code: start engine
        engineCore = new EngineCore(this);
        engineCore.start();

        // 【!】Code: register command
        getCommand("aoiengine").setExecutor(new EngineCommand());

        getLogger().info("AoiEngine Enabled.");
    }

    @Override
    public void onDisable() {

        // 【!】Code: shutdown engine
        if(engineCore != null){
            engineCore.shutdown();
        }

        getLogger().info("AoiEngine Disabled.");
    }
}
