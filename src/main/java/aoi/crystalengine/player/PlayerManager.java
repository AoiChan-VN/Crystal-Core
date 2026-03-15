package com.aoi.crystalengine.player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/*
#【!】Code:
Quản lý toàn bộ CrystalPlayer
*/

public class PlayerManager {

    private Map<UUID, CrystalPlayer> players = new HashMap<>();

    public CrystalPlayer get(UUID uuid) {

        if (!players.containsKey(uuid)) {
            players.put(uuid, new CrystalPlayer(uuid));
        }

        return players.get(uuid);
    }

} 
