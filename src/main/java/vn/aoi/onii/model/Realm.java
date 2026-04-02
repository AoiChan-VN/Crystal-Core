package vn.aoi.onii.model;

import java.util.Map;

public class Realm {
    private final String name;
    private final int max;
    private final Map<Integer, LevelInfo> levels;
    private final String next;
    private final boolean trib;

    public Realm(String name, int max, Map<Integer, LevelInfo> levels, String next, boolean trib) {
        this.name = name;
        this.max = max;
        this.levels = levels;
        this.next = next;
        this.trib = trib;
    }

    public String getName() { return name; }
    public int getMax() { return max; }
    public Map<Integer, LevelInfo> getLevels() { return levels; }
    public String getNext() { return next; }
    public boolean isTrib() { return trib; }
} 
