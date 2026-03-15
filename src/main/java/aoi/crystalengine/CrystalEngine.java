package com.aoi.crystalengine;

import org.bukkit.plugin.java.JavaPlugin;

import com.aoi.crystalengine.bootstrap.EngineBootstrap;
import com.aoi.crystalengine.util.EngineLog;

/*
#【!】Code:
Main engine entry.
Tất cả hệ thống được khởi tạo từ EngineBootstrap.
*/

public class CrystalEngine extends JavaPlugin {

    private static CrystalEngine instance;
    private EngineBootstrap bootstrap;

    @Override
    public void onEnable() {

        instance = this;

        EngineLog.info("Starting CrystalEngine Core...");

        bootstrap = new EngineBootstrap(this);
        bootstrap.start();

        EngineLog.success("CrystalEngine Core started.");
    }

    @Override
    public void onDisable() {

        EngineLog.warn("CrystalEngine shutting down...");
        bootstrap.shutdown();
    }

    public static CrystalEngine get() {
        return instance;
    }

    public EngineBootstrap getBootstrap() {
        return bootstrap;
    }

}
