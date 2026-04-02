package vn.aoi.onii.manager;

import vn.aoi.onii.data.PlayerData;
import java.util.*;

public class PlayerManager {

    private static final Map<UUID,PlayerData> map=new HashMap<>();

    public static PlayerData get(UUID u){return map.get(u);}
    public static void set(PlayerData d){map.put(d.uuid,d);}
    public static void remove(UUID u){map.remove(u);}
    public static Collection<PlayerData> all(){return map.values();}
}
