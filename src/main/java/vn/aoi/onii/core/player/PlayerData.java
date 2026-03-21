// File: src/main/java/vn/aoi/onii/core/player/PlayerData.java
package vn.aoi.onii.core.player;

import java.util.UUID;

public class PlayerData {

    private final UUID uuid;
    private int level;
    private int exp;

    public PlayerData(UUID uuid) {
        this.uuid = uuid;
        this.level = 1;
        this.exp = 0;
    }

    public UUID getUuid() { return uuid; }

    public int getLevel() { return level; }

    public void setLevel(int level) { this.level = level; }

    public int getExp() { return exp; }

    public void setExp(int exp) { this.exp = exp; }
} 
