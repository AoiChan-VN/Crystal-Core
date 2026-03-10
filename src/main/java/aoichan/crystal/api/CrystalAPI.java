package aoichan.crystal.api;

import org.bukkit.entity.Player;

// [!] Code: Public API entry point
public class CrystalAPI {

    private static GemService gemService;

    // [!] Code: internal use
    public static void setGemService(GemService service) {
        gemService = service;
    }

    // [!] Code: give gem to player
    public static void giveGem(
            Player player,
            String gemId,
            int level
    ) {

        if (gemService == null)
            return;

        gemService.giveGem(
                player,
                gemId,
                level
        );
    }

    // [!] Code: remove gem
    public static void takeGem(
            Player player,
            String gemId
    ) {

        if (gemService == null)
            return;

        gemService.takeGem(
                player,
                gemId
        );
    }

    // [!] Code: check gem
    public static boolean hasGem(
            Player player,
            String gemId
    ) {

        if (gemService == null)
            return false;

        return gemService.hasGem(
                player,
                gemId
        );
    }

} 
