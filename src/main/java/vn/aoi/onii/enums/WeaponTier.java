package vn.aoi.onii.enums;

public enum WeaponTier {

    PHAP_KHI("Pháp Khí", 1),
    LINH_KHI("Linh Khí", 2),
    PHAP_BAO("Pháp Bảo", 3),
    CO_BAO("Cổ Bảo", 4),
    LINH_BAO("Linh Bảo", 5),
    TIEN_KHI("Tiên Khí", 6),
    THAN_KHI("Thần Khí", 7);

    private final String display;
    private final int level;

    WeaponTier(String display, int level) {
        this.display = display;
        this.level = level;
    }

    public String getDisplay() {
        return display;
    }

    public int getLevel() {
        return level;
    }

    public static WeaponTier fromString(String s) {
        try {
            return WeaponTier.valueOf(s.toUpperCase());
        } catch (Exception e) {
            return PHAP_KHI;
        }
    }
}
