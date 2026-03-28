package vn.aoi.onii.enums;

public enum TechniqueTier {

    PHAM("Phàm", 1),
    LINH("Linh", 2),
    HUYEN("Huyền", 3),
    DIA("Địa", 4),
    THIEN("Thiên", 5),
    THAN("Thần", 6);

    private final String display;
    private final int level;

    TechniqueTier(String display, int level) {
        this.display = display;
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public static TechniqueTier fromString(String s) {
        try {
            return TechniqueTier.valueOf(s.toUpperCase());
        } catch (Exception e) {
            return PHAM;
        }
    }
}
