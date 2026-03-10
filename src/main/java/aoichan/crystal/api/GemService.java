package aoichan.crystal.api;

import org.bukkit.entity.Player;

// [!] Code: API interface
public interface GemService {

    void giveGem(
            Player player,
            String gemId,
            int level
    );

    void takeGem(
            Player player,
            String gemId
    );

    boolean hasGem(
            Player player,
            String gemId
    );

} 
