package aoichan.crystal.api;

import java.util.UUID;

// [!] Code: Gem Data Object
public class GemData {

    private final UUID uuid;
    private int amount;

    public GemData(UUID uuid, int amount) {
        this.uuid = uuid;
        this.amount = amount;
    }

    public UUID getUuid() {
        return uuid;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
