package vn.aoi.onii.manager.model;

public class LevelInfo {

    private final int exp;
    private final String phase;

    public LevelInfo(int exp, String phase) {
        this.exp = exp;
        this.phase = phase;
    }

    public int getExp() { return exp; }
    public String getPhase() { return phase; }
} 
