package vn.aoi.onii.player;

public class PlayerData {

    private String realm;
    private int level;
    private int exp;

    public PlayerData(String realm, int level, int exp) {
        this.realm = realm;
        this.level = level;
        this.exp = exp;
    }

    public String getRealm() { return realm; }
    public void setRealm(String realm) { this.realm = realm; }

    public int getLevel() { return level; }
    public void setLevel(int level) { this.level = level; }

    public int getExp() { return exp; }
    public void addExp(int amount) { this.exp += amount; }
    public void setExp(int exp) { this.exp = exp; }
} 
