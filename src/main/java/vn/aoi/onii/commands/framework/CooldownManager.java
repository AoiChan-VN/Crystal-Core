package vn.aoi.onii.commands.framework;

import java.util.*;

public class CooldownManager {

    private static final Map<String, Long> cooldowns = new HashMap<>();

    public static boolean isOnCooldown(String key, int seconds) {
        long now = System.currentTimeMillis();

        if (!cooldowns.containsKey(key)) return false;

        long last = cooldowns.get(key);
        return (now - last) < (seconds * 1000L);
    }

    public static long getRemaining(String key, int seconds) {
        long now = System.currentTimeMillis();
        long last = cooldowns.getOrDefault(key, 0L);
        return seconds - ((now - last) / 1000);
    }

    public static void setCooldown(String key) {
        cooldowns.put(key, System.currentTimeMillis());
    }
}
