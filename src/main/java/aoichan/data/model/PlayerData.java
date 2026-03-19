package aoichan.data.model;

import java.util.UUID;

public class PlayerData {

    private final UUID uuid;
    private int coins;

    public PlayerData(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }
} 
