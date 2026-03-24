package vn.aoi.onii.combat;

public class Buff {

    public String id;
    public long expireAt;

    public double str;
    public double agi;
    public double intel;
    public double def;
    public double crit;

    public Buff(String id, long duration) {
        this.id = id;
        this.expireAt = System.currentTimeMillis() + duration;
    }

    public boolean isExpired() {
        return System.currentTimeMillis() > expireAt;
    }
}
