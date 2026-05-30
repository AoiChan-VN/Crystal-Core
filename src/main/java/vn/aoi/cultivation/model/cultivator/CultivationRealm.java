package vn.aoi.cultivation.model.cultivator;

public enum CultivationRealm {

    LUYEN_KHI(1.0, false),
    TRUC_CO(2.5, true),
    KIM_DAN(6.25, false),
    NGUYEN_ANH(15.6, true),
    HOA_THAN(39.1, false),
    LUYEN_HU(97.7, true),
    HOP_THE(244.1, false),
    DAI_THUA(610.4, true),
    DO_KIEP(1525.9, false);

    private final double multiplier;
    private final boolean tribulation;

    CultivationRealm(double multiplier, boolean tribulation) {
        this.multiplier = multiplier;
        this.tribulation = tribulation;
    }

    public double multiplier() {
        return multiplier;
    }

    public boolean requiresTribulation() {
        return tribulation;
    }

    public boolean isPeak() {
        return this == DO_KIEP;
    }
} 
