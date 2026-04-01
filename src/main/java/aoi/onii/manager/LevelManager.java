package vn.aoi.onii.manager;

import org.bukkit.entity.Player;
import vn.aoi.onii.config.ConfigManager;
import vn.aoi.onii.data.PlayerData;
import vn.aoi.onii.task.ThunderTask;

public class LevelManager {

    public static void checkLevelUp(Player player, PlayerData data) {

        String realm = data.getRealm();
        int level = data.getLevel();

        if (!ConfigManager.realms.contains("realms." + realm)) return;

        int required = ConfigManager.realms.getInt("realms." + realm + ".levels." + level + ".exp");

        if (data.getExp() >= required) {
            data.setLevel(level + 1);
            data.addExp(-required);

            player.sendMessage("§aĐột phá thành công!");

            if (ConfigManager.realms.getBoolean("realms." + realm + ".is-thien-kiep")) {
                new ThunderTask(player).start();
            }
        }
    }
} 
