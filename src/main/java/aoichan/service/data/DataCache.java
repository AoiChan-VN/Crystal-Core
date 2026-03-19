package aoichan.service.data;

import aoichan.data.model.PlayerData;

import java.util.*;

public class DataCache {

    private final Map<UUID, PlayerData> cache = new HashMap<>();

    public PlayerData get(UUID uuid) {
        return cache.get(uuid);
    }

    public void put(PlayerData data) {
        cache.put(data.getUuid(), data);
    }

    public Collection<PlayerData> all() {
        return cache.values();
    }
} 
