package vn.aoi.onii.player;

public class PlayerData {
    private String rank;
    private int level;
    private int exp;

    public PlayerData(String r,int l,int e){rank=r;level=l;exp=e;}
    public String getRank(){return rank;}
    public int getLevel(){return level;}
    public int getExp(){return exp;}
    public void addExp(int a){exp+=a;}
    public void setLevel(int l){level=l;}
    public void setRank(String r){rank=r;}
}
