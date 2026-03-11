 package aoichan.crystal.core.socket;

// [!] Code: Socket gem data
public class SocketData {

    private final String gemId;
    private final int level;

    public SocketData(
            String gemId,
            int level
    ) {

        this.gemId = gemId;
        this.level = level;
    }

    public String getGemId() {
        return gemId;
    }

    public int getLevel() {
        return level;
    }
}
