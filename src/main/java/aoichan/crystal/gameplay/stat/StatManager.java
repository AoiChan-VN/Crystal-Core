package aoichan.crystal.gameplay.stat;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class StatManager {

    private static final Map<UUID, PlayerStatProfile> cache = new HashMap<>();

    public static PlayerStatProfile get(Player player) {

        UUID uuid = player.getUniqueId();

        if (!cache.containsKey(uuid)) {

            PlayerStatProfile profile = StatCalculator.calculate(player);

            cache.put(uuid, profile);

        }

        return cache.get(uuid);

    }

    public static void recalc(Player player) {

        // 【!】Code: recalculates stat
        PlayerStatProfile profile = StatCalculator.calculate(player);

        cache.put(player.getUniqueId(), profile);

    }

    public static void remove(Player player) {

        cache.remove(player.getUniqueId());

    }

}
 
