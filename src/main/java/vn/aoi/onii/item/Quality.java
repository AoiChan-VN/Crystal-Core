package vn.aoi.onii.item;

public enum Quality {
    PHAM(1),
    LINH(2),
    DIA(3),
    THIEN(5);

    private final int multiplier;

    Quality(int multiplier) {
        this.multiplier = multiplier;
    }

    public int getMultiplier() { return multiplier; }
} 
