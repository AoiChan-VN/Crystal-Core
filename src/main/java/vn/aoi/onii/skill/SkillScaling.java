package vn.aoi.onii.skill;

import vn.aoi.onii.combat.*;
import vn.aoi.onii.player.PlayerData;

public class SkillScaling {

    public static double scale(PlayerData data, double base) {

        StatProfile s = data.getStats();

        double str = s.get(StatType.STR);
        double agi = s.get(StatType.AGI);
        double intel = s.get(StatType.INT);

        double result = base;

        result += str * 0.8;
        result += agi * 0.4;
        result += intel * 1.2;

        return result;
    }
} 
