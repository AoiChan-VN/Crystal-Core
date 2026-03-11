package aoichan.crystal.infrastructure.util;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

// [!] Code: Sound util
public class SoundUtil {

    public static void success(Player p) {

        p.playSound(
                p.getLocation(),
                Sound.BLOCK_NOTE_BLOCK_BELL,
                1f,
                1.2f
        );

    }

    public static void fail(Player p) {

        p.playSound(
                p.getLocation(),
                Sound.BLOCK_ANVIL_BREAK,
                1f,
                1f
        );

    }

} 
