package aoichan.crystal.core;

import aoichan.crystal.AoiMain;

public class ReloadManager {

    public static void reload() {

        AoiMain plugin = AoiMain.get();

        plugin.reloadConfig();
        plugin.getGemsManager().reload();

        plugin.getLogger().info("GemsUltimate reloaded without restart.");
    }
}
