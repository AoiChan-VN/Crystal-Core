package aoichan.crystal.infrastructure.broadcast;

import aoichan.crystal.gameplay.gem.Gem;
import aoichan.crystal.gameplay.gem.GemRarity;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class BroadcastManager {

    public static void broadcastTribulation(Player player, Gem gem) {

        // 【!】Code: chỉ broadcast nếu gem hiếm
        if (gem.getRarity().ordinal() < GemRarity.RARE.ordinal()) {
            return;
        }

        TextComponent msg = new TextComponent(
                "§6【Thiên Đạo】: §f" + player.getName() + " đang độ kiếp..."
        );

        TextComponent hover = new TextComponent(
                "§b➩: " + gem.getName() + "\n§eRarity: " + gem.getRarity()
        );

        msg.setHoverEvent(new HoverEvent(
                HoverEvent.Action.SHOW_TEXT,
                new ComponentBuilder(hover.getText()).create()
        ));

        Bukkit.spigot().broadcast(msg);

        // 【!】Code: sound sấm sét
        for (Player p : Bukkit.getOnlinePlayers()) {

            p.playSound(
                    p.getLocation(),
                    Sound.ENTITY_LIGHTNING_BOLT_THUNDER,
                    1f,
                    1f
            );

        }

    }

    public static void successBell(Player player) {

        // 【!】Code: sound chuông khi thành công
        for (Player p : Bukkit.getOnlinePlayers()) {

            p.playSound(
                    p.getLocation(),
                    Sound.BLOCK_BELL_USE,
                    1f,
                    1.2f
            );

        }

    }

} 
