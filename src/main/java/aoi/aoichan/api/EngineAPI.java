package aoi.aoichan.api;

import aoi.aoichan.AoiMain;
import aoi.aoichan.module.ModuleLoader;

/*
 API cho các plugin khác sử dụng CrystalEngine
*/

public class EngineAPI {

    private static AoiMain plugin;

    // 【!】Code: khởi tạo API
    public static void initialize(AoiMain instance) {
        plugin = instance;
    }

    // 【!】Code: lấy plugin instance
    public static AoiMain getPlugin() {
        return plugin;
    }

    // 【!】Code: truy cập module loader
    public static ModuleLoader getModuleLoader() {
        return plugin.getModuleLoader();
    }
} 
