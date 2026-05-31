package vn.aoi.cultivation.model.sect;

public enum SectRank {

    NGOAI_MON(1),
    NOI_MON(2),
    TRUONG_LAO(3),
    CHUONG_MON(4);

    private final int authority;

    SectRank(int authority) {
        this.authority = authority;
    }

    public int authority() {
        return authority;
    }

    public boolean canPromote(SectRank target) {
        return this.authority > target.authority;
    }

    public boolean canDemote(SectRank target) {
        return this.authority < target.authority;
    }

    public boolean isLeader() {
        return this == CHUONG_MON;
    }
}
