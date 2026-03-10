package aoichan.crystal.gameplay.stat;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerStatCache {

    private static final Map<UUID, Map<String,Integer>>
            cache = new HashMap<>();

    // [!] Code: Get stats
    public static Map<String,Integer> get(Player player) {

        return cache.getOrDefault(
                player.getUniqueId(),
                new HashMap<>()
        );
    }

    // [!] Code: Set stats
    public static void set(
            Player player,
            Map<String,Integer> stats
    ) {

        cache.put(player.getUniqueId(), stats);
    }

    // [!] Code: Clear cache
    public static void clear(Player player) {

        cache.remove(player.getUniqueId());
    }
} 
