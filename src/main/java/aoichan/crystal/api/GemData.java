package aoichan.crystal.api;

public class GemData {
    private final String id;
    private final String displayName;

    public GemData(String id, String displayName) {
        this.id = id;
        this.displayName = displayName;
    }

    public String getId() { return id; }
    public String getDisplayName() { return displayName; }
}
