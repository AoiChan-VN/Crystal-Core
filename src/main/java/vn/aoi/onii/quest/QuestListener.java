package vn.aoi.onii.quest;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import vn.aoi.onii.player.PlayerManager;

public class QuestListener implements Listener {

    private final QuestManager questManager;
    private final PlayerManager playerManager;

    public QuestListener(QuestManager questManager, PlayerManager playerManager) {
        this.questManager = questManager;
        this.playerManager = playerManager;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        playerManager.load(e.getPlayer());
    }
}
