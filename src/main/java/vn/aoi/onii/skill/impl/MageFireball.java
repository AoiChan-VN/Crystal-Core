package vn.aoi.onii.skill.impl;

import org.bukkit.entity.Player;
import org.bukkit.entity.SmallFireball;
import vn.aoi.onii.skill.Skill;
import vn.aoi.onii.classsystem.ClassContext;

public class MageFireball implements Skill {

    @Override
    public String getId() { return "fireball"; }

    @Override
    public long getCooldown() { return 5000; }

    @Override
    public void execute(ClassContext ctx) {
        Player p = ctx.getPlayer();
        SmallFireball fb = p.launchProjectile(SmallFireball.class);
        fb.setYield(1);
    }
}
