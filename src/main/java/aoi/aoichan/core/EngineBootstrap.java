package aoi.aoichan.core;

import aoi.aoichan.AoiMain;
import aoi.aoichan.module.ModuleManager;
import aoi.aoichan.server.TPSMonitor;
import aoi.aoichan.server.MSPTMonitor;
import aoi.aoichan.database.DatabaseManager;

/*
 Bootstrap toàn bộ hệ thống engine
*/

public class EngineBootstrap {

    private final AoiMain plugin;

    private ModuleManager moduleManager;
    private TPSMonitor tpsMonitor;
    private MSPTMonitor msptMonitor;
    private DatabaseManager database;

    public EngineBootstrap(AoiMain plugin) {
        this.plugin = plugin;
    }

    public void start() {

        // 【!】Code: load config
        EngineConfig.load(plugin);

        // 【!】Code: database
        database = new DatabaseManager(plugin);
        database.connect();

        // 【!】Code: module manager
        moduleManager = new ModuleManager();

        // 【!】Code: monitor server
        tpsMonitor = new TPSMonitor(plugin);
        msptMonitor = new MSPTMonitor(plugin);

        tpsMonitor.start();
        msptMonitor.start();

        EngineLogger.log("§a[AoiEngine] CrystalEngine V2 started.");

    }

    public void shutdown() {

        if (tpsMonitor != null) tpsMonitor.stop();
        if (msptMonitor != null) msptMonitor.stop();

        if (database != null) database.disconnect();

        EngineLogger.log("§c[AoiEngine] Engine stopped.");

    }

}
