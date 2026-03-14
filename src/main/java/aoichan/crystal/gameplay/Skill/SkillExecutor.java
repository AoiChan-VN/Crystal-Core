package aoichan.crystal.gameplay.skill;

import aoichan.crystal.gameplay.stat.PlayerStatProfile;
import aoichan.crystal.gameplay.stat.StatManager;
import aoichan.crystal.gameplay.stat.StatType;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class SkillExecutor {

    public static void cast(Player player, Entity target, Skill skill) {

        // 【!】Code: check cooldown
        if (SkillCooldownManager.isCooldown(player, skill.getId())) {

            player.sendMessage("§cđang hồi chiêu!");

            return;
        }

        PlayerStatProfile stat = StatManager.get(player);

        double baseDamage = stat.getStat(StatType.DAMAGE);

        double finalDamage = baseDamage * skill.getDamageScale();

        if (target instanceof org.bukkit.entity.LivingEntity) {

            ((org.bukkit.entity.LivingEntity) target).damage(finalDamage, player);

        }

        // 【!】Code: set cooldown
        SkillCooldownManager.setCooldown(
                player,
                skill.getId(),
                skill.getCooldown()
        );

    }

}
 
