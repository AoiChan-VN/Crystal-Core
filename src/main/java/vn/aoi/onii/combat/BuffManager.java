package vn.aoi.onii.combat;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class BuffManager {

    private final Map<UUID, List<Buff>> buffs = new ConcurrentHashMap<>();

    public void addBuff(UUID uuid, Buff buff) {
        buffs.computeIfAbsent(uuid, k -> new ArrayList<>()).add(buff);
    }

    public List<Buff> getBuffs(UUID uuid) {
        List<Buff> list = buffs.getOrDefault(uuid, Collections.emptyList());

        list.removeIf(Buff::isExpired);
        return list;
    }

    public StatProfile apply(UUID uuid, StatProfile base) {
        StatProfile result = new StatProfile();

        for (StatType t : StatType.values()) {
            result.set(t, base.get(t));
        }

        for (Buff b : getBuffs(uuid)) {
            result.add(StatType.STR, b.str);
            result.add(StatType.AGI, b.agi);
            result.add(StatType.INT, b.intel);
            result.add(StatType.DEF, b.def);
            result.add(StatType.CRIT, b.crit);
        }

        return result;
    }
} 
