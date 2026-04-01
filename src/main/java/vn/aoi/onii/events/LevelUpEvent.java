package vn.aoi.onii.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class LevelUpEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private final Player player;
    private final int newLevel;

    public LevelUpEvent(Player player, int newLevel) {
        this.player = player;
        this.newLevel = newLevel;
    }

    public Player getPlayer() { return player; }
    public int getNewLevel() { return newLevel; }

    @Override
    public HandlerList getHandlers() { return handlers; }
    public static HandlerList getHandlerList() { return handlers; }
} 
