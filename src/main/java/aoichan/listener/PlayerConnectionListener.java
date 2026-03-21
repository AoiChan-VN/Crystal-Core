// ========================= LISTENER =========================
package aoichan.listener;

import aoichan.service.player.PlayerSessionManager;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * 【❅】 Player lifecycle listener
 */
public class PlayerConnectionListener implements Listener {

    private final PlayerSessionManager sessionManager;

    public PlayerConnectionListener(PlayerSessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        sessionManager.load(e.getPlayer());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        sessionManager.unload(e.getPlayer().getUniqueId());
    }
}
