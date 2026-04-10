package vn.aoi.onii.task;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import vn.aoi.onii.api.PlayerLevelUpEvent;
import vn.aoi.onii.manager.PlayerManager;
import vn.aoi.onii.manager.RealmManager;
import vn.aoi.onii.model.Cultivator;

import java.util.Random;

public class TribulationTask extends BukkitRunnable {

    private final Player player;
    private final PlayerManager playerManager;
    private final RealmManager realmManager;
    private final int totalStrikes;
    
    private int strikesDone = 0;
    private final Random random = new Random();

    public TribulationTask(Player player, PlayerManager playerManager, RealmManager realmManager, int strikes) {
        this.player = player;
        this.playerManager = playerManager;
        this.realmManager = realmManager;
        this.totalStrikes = strikes;

        // Làm trời tối sầm lại (Chỉ riêng Player này thấy)
        // 18000 là thời điểm nửa đêm trong Minecraft
        player.setPlayerTime(18000, false);
        player.sendMessage("§8[!] Thiên địa biến sắc, lôi kiếp đang tụ lại...");
    }

    @Override
    public void run() {
        if (!player.isOnline() || player.isDead()) {
            endTribulation();
            this.cancel();
            return;
        }

        if (strikesDone >= totalStrikes) {
            success();
            endTribulation();
            this.cancel();
            return;
        }

        Location loc = player.getLocation();
        strikesDone++;

        // Sét đánh trực diện từ trên cao xuống vị trí Player
        Location skyLoc = loc.clone();
        skyLoc.setY(loc.getWorld().getHighestBlockYAt(loc));
        loc.getWorld().strikeLightning(skyLoc);

        // Gây sát thương tăng dần (bỏ qua giáp nếu muốn bằng cách dùng damage trực tiếp)
        double damageAmount = 2.0 + (strikesDone * 2.0); 
        player.damage(damageAmount);

        // Âm thanh uy lực
        player.playSound(loc, org.bukkit.Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 1.2f, 0.5f + (strikesDone * 0.1f));
        
        player.sendMessage("§c⚡ Đạo lôi kiếp thứ " + strikesDone + " giáng xuống!");
        
        // Hiệu ứng mù quáng nhẹ mỗi lần sét đánh trúng
        player.addPotionEffect(new org.bukkit.potion.PotionEffect(
                org.bukkit.potion.PotionEffectType.BLINDNESS, 40, 0));
    }

    private void success() {
        Cultivator c = playerManager.get(player.getUniqueId());
        if (c == null) return;

        String oldRealm = c.getRealm();
        String nextRealm = realmManager.getRealm(oldRealm).getNextRank();

        c.setRealm(nextRealm);
        c.setLevel(1);
        c.setExp(0);

        Bukkit.getPluginManager().callEvent(new PlayerLevelUpEvent(player, oldRealm, nextRealm, 1));
        player.sendMessage("§b⚡ Chúc mừng! Bạn đã đột phá thành công lên " + nextRealm);
        player.playSound(player.getLocation(), org.bukkit.Sound.UI_TOAST_CHALLENGE_COMPLETE, 1f, 1f);
    }

    private void endTribulation() {
        // Trả lại thời gian thực của Server cho Player
        if (player.isOnline()) {
            player.resetPlayerTime();
        }
    }
}
