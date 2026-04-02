package vn.aoi.onii.manager;

import vn.aoi.onii.config.ConfigManager;
import vn.aoi.onii.model.*;

import java.util.*;

public class RealmManager {

    private static final Map<String, Realm> map = new HashMap<>();

    public static void load() {
        map.clear();

        var root = ConfigManager.realms.getConfigurationSection("realms");
        if (root == null) return;

        for (String key : root.getKeys(false)) {

            int max = root.getInt(key + ".max-level");
            String next = root.getString(key + ".next-rank");
            boolean trib = root.getBoolean(key + ".is-thien-kiep");

            Map<Integer, LevelInfo> levels = new HashMap<>();

            var sec = root.getConfigurationSection(key + ".levels");
            if (sec != null) {
                for (String l : sec.getKeys(false)) {
                    levels.put(Integer.parseInt(l),
                            new LevelInfo(sec.getInt(l + ".exp")));
                }
            }

            map.put(key, new Realm(key, max, levels, next, trib));
        }
    }

    public static Realm get(String name) {
        return map.get(name);
    }
} 
