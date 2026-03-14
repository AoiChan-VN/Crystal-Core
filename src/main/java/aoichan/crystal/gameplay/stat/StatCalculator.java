package aoichan.crystal.gameplay.stat;

import aoichan.crystal.gameplay.gem.Gem;
import aoichan.crystal.gameplay.gem.GemManager;
import aoichan.crystal.gameplay.gem.GemStat;
import aoichan.crystal.gameplay.socket.SocketManager;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Map;

public class StatCalculator {

    public static PlayerStatProfile calculate(Player player) {

        PlayerStatProfile profile = new PlayerStatProfile();

        // 【!】Code: lấy toàn bộ item player
        ItemStack mainHand = player.getInventory().getItemInMainHand();

        if (mainHand != null) {
            applyItem(profile, mainHand);
        }

        for (ItemStack armor : player.getInventory().getArmorContents()) {

            if (armor != null) {
                applyItem(profile, armor);
            }

        }

        return profile;

    }

    private static void applyItem(PlayerStatProfile profile, ItemStack item) {

        List<Gem> gems = SocketManager.getGems(item);

        for (Gem gem : gems) {

            Map<GemStat, Double> stats = gem.getStats();

            for (GemStat gemStat : stats.keySet()) {

                StatType type = convert(gemStat);

                double value = stats.get(gemStat);

                profile.addStat(type, value);

            }

        }

    }

    private static StatType convert(GemStat stat) {

        // 【!】Code: convert gem stat -> player stat
        switch (stat) {

            case DAMAGE:
                return StatType.DAMAGE;

            case CRIT_RATE:
                return StatType.CRIT_RATE;

            case CRIT_DAMAGE:
                return StatType.CRIT_DAMAGE;

            case DEFENSE:
                return StatType.DEFENSE;

            case MAX_HEALTH:
                return StatType.MAX_HEALTH;

            case MAX_MANA:
                return StatType.MAX_MANA;

            case MANA_REGEN:
                return StatType.MANA_REGEN;

            case LIFE_STEAL:
                return StatType.LIFE_STEAL;

            case SKILL_DAMAGE:
                return StatType.SKILL_DAMAGE;

            case ATTACK_SPEED:
                return StatType.ATTACK_SPEED;

        }

        return StatType.DAMAGE;

    }

}
 
