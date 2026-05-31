package vn.aoi.cultivation.model.cultivator;

public enum SpiritElement {

    KIM,
    MOC,
    THO,
    THUY,
    HOA;

    /**
     * Chuẩn ngũ hành tương khắc:
     * Kim → Mộc → Thổ → Thủy → Hỏa → Kim
     */
    public boolean counters(SpiritElement other) {
        return switch (this) {
            case KIM -> other == MOC;
            case MOC -> other == THO;
            case THO -> other == THUY;
            case THUY -> other == HOA;
            case HOA -> other == KIM;
        };
    }

    /**
     * Bonus nhẹ cho same-element resonance (optional hook)
     */
    public boolean resonatesWith(SpiritElement other) {
        return this == other;
    }
}
