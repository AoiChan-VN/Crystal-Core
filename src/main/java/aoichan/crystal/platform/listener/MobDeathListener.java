package aoichan.crystal.platform.listener;

import aoichan.crystal.gameplay.drop.GemDropManager;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class MobDeathListener implements Listener {

    @EventHandler
    public void onMobDeath(EntityDeathEvent event) {

        LivingEntity mob = event.getEntity();

        // [!] Code: Roll gem drop
        ItemStack gem =
                GemDropManager.roll(mob.getType());

        if (gem == null) return;

        mob.getWorld().dropItemNaturally(
                mob.getLocation(),
                gem
        );
    }
} 
