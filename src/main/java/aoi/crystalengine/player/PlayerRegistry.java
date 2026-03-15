package com.aoi.crystalengine.player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/*
#【!】Code:
Quản lý toàn bộ CrystalPlayer
*/

public class PlayerRegistry {

    private final Map<UUID, CrystalPlayer> players = new HashMap<>();

    public CrystalPlayer get(UUID uuid) {

        return players.computeIfAbsent(uuid, CrystalPlayer::new);
    }

} 
