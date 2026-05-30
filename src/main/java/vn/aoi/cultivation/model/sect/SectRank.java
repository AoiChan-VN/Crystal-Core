package vn.aoi.cultivation.model.sect;

public enum SectRank {

    CHUONG_MON(1),   // leader
    TRUONG_LAO(2),   // elders
    NOI_MON(3),      // inner disciples
    NGOAI_MON(4);    // outer disciples

    private final int authority;

    SectRank(int authority) {
        this.authority = authority;
    }

    public int authority() {
        return authority;
    }

    public boolean canPromote(SectRank target) {
        return this.authority < target.authority;
    }

    public boolean isLeader() {
        return this == CHUONG_MON;
    }
} 
