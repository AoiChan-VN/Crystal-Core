package vn.aoi.onii.skill;

import org.bukkit.entity.Player;

import vn.aoi.onii.player.PlayerManager;
import vn.aoi.onii.player.PlayerData;
import vn.aoi.onii.classsystem.ClassContext;
import vn.aoi.onii.combat.BuffManager;

import java.util.*;

public class SkillManager {

    private final Map<String, Skill> skills = new HashMap<>();
    private final Map<UUID, Map<String, Long>> cooldowns = new HashMap<>();

    private final SkillConfigManager configManager;
    private final PlayerManager playerManager;
    private final BuffManager buffManager;

    public SkillManager(SkillConfigManager configManager, PlayerManager playerManager, BuffManager buffManager) {
        this.configManager = configManager;
        this.playerManager = playerManager;
        this.buffManager = buffManager;
    }

    public void register(Skill skill) {
        skills.put(skill.getId(), skill);
    }

    public void use(Player player, String skillId) {

        Skill skill = skills.get(skillId);
        if (skill == null) return;

        PlayerData data = playerManager.get(player);
        if (data == null) return;

        SkillConfig cfg = configManager.get(skillId);
        if (cfg == null) return;

        if (!data.hasMana(cfg.mana)) {
            player.sendMessage("§cNot enough mana");
            return;
        }

        if (isCooldown(player.getUniqueId(), skillId)) {
            player.sendMessage("§eSkill cooling down...");
            return;
        }

        data.consumeMana(cfg.mana);

        ClassContext ctx = new ClassContext(player, data, buffManager);
        skill.execute(ctx);

        setCooldown(player.getUniqueId(), skillId, cfg.cooldown);
    }

    private boolean isCooldown(UUID uuid, String skillId) {
        Map<String, Long> map = cooldowns.getOrDefault(uuid, Collections.emptyMap());
        long time = map.getOrDefault(skillId, 0L);
        return System.currentTimeMillis() < time;
    }

    private void setCooldown(UUID uuid, String skillId, long cd) {
        cooldowns.computeIfAbsent(uuid, k -> new HashMap<>())
                .put(skillId, System.currentTimeMillis() + cd);
    }
}
