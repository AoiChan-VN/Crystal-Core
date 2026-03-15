package aoi.aoichan;

import org.bukkit.plugin.java.JavaPlugin;
import aoi.aoichan.core.EngineBootstrap;

/*
 CrystalEngine CORE
 Paper 1.21.1
 Java 21
*/

public final class AoiMain extends JavaPlugin {

    private static AoiMain instance;
    private EngineBootstrap bootstrap;

    @Override
    public void onEnable() {

        instance = this;

        // 【!】Code: khởi động hệ thống engine
        bootstrap = new EngineBootstrap(this);
        bootstrap.start();

    }

    @Override
    public void onDisable() {

        // 【!】Code: shutdown engine
        if (bootstrap != null) {
            bootstrap.shutdown();
        }

    }

    // 【!】Code: lấy instance plugin
    public static AoiMain getInstance() {
        return instance;
    }

}
