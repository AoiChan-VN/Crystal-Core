package vn.aoi.onii.core;

import vn.aoi.onii.Main;
import vn.aoi.onii.data.Database;
import vn.aoi.onii.manager.*;

public class Bootstrap {

    public static void init(Main plugin) {
        ConfigManager.init(plugin);
        Database.init(plugin);
        PlayerManager.init();
        RealmManager.init();
        CommandManager.init(plugin);
        ListenerManager.init(plugin);
    }

    public static void shutdown() {
        Database.shutdown();
    }
} 
