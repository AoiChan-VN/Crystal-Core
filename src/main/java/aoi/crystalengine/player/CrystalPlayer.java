package com.aoi.crystalengine.player;

import java.util.UUID;

/*
#【!】Code:
Player wrapper cho toàn bộ engine data
*/

public class CrystalPlayer {

    private final UUID uuid;

    public CrystalPlayer(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUUID() {
        return uuid;
    }

} 
