package vn.aoi.cultivation.service;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class DropService {

    /**
     * Chống spam kill/re-entity exploit
     */
    private final ConcurrentHashMap<UUID, Long> mobCooldownMap = new ConcurrentHashMap<>();

    private static final long MOB_COOLDOWN_MS = 1000L;

    /**
     * RNG engine riêng để tránh phụ thuộc global randomness
     */
    private final Random rng = new Random();

    /**
     * Xử lý drop khi mob chết
     */
    public void handleMobDrop(Player killer,
                              Entity mob,
                              List<Map<String, Object>> dropTable) {

        if (killer == null || mob == null || dropTable == null) return;

        UUID mobId = mob.getUniqueId();
        long now = System.currentTimeMillis();

        Long last = mobCooldownMap.get(mobId);
        if (last != null && (now - last) < MOB_COOLDOWN_MS) {
            return;
        }

        mobCooldownMap.put(mobId, now);

        Location loc = mob.getLocation();
        World world = mob.getWorld();

        int playerLevel = killer.getLevel();

        for (Map<String, Object> entry : dropTable) {

            ItemStack item = (ItemStack) entry.get("item");
            if (item == null) continue;

            double baseChance = (double) entry.getOrDefault("chance", 0.0);
            int minAmount = (int) entry.getOrDefault("min", 1);
            int maxAmount = (int) entry.getOrDefault("max", 1);

            double finalChance = applyScaling(baseChance, playerLevel);

            if (rng.nextDouble() <= finalChance) {

                ItemStack drop = item.clone();

                int amount = resolveAmount(minAmount, maxAmount, playerLevel);
                drop.setAmount(Math.max(1, Math.min(amount, 64)));

                world.dropItemNaturally(loc, drop);
            }
        }
    }

    /**
     * Scale chance theo level người chơi (anti-farm imbalance control)
     */
    private double applyScaling(double baseChance, int level) {

        double levelMultiplier;

        if (level <= 10) {
            levelMultiplier = 0.85;
        } else if (level <= 30) {
            levelMultiplier = 1.0;
        } else if (level <= 60) {
            levelMultiplier = 1.15;
        } else {
            levelMultiplier = 1.25;
        }

        double result = baseChance * levelMultiplier;

        return Math.max(0.0, Math.min(result, 0.95));
    }

    /**
     * Tính số lượng drop theo level (bounded RNG)
     */
    private int resolveAmount(int min, int max, int level) {

        int dynamicMax = max + (level / 20);

        if (dynamicMax < min) {
            dynamicMax = min;
        }

        return min + rng.nextInt(dynamicMax - min + 1);
    }

    /**
     * Cleanup toàn bộ cache
     */
    public void clear() {
        mobCooldownMap.clear();
    }
} 
