// ========================= PACKAGE =========================
package aoichan.service.player;

import aoichan.data.model.PlayerData;
import aoichan.service.data.DataCache;
import aoichan.service.data.SaveQueue;

import org.bukkit.entity.Player;

import java.util.*;

/**
 * 【❅】 PlayerSessionManager
 * Full lifecycle: load → cache → dirty → save → unload
 */
public class PlayerSessionManager {

    private final Map<UUID, PlayerSession> sessions = new HashMap<>();

    private final DataCache dataCache;
    private final SaveQueue saveQueue;

    public PlayerSessionManager(DataCache dataCache, SaveQueue saveQueue) {
        this.dataCache = dataCache;
        this.saveQueue = saveQueue;
    }

    // ========================= LOAD =========================

    /**
     * 【❅】 Load player session (async-safe, call from main AFTER data ready)
     */
    public PlayerSession load(Player player) {
        UUID uuid = player.getUniqueId();

        // 【❅】 Get PlayerData from cache (already loaded async by Core)
        PlayerData data = dataCache.get(uuid);

        PlayerSession session = new PlayerSession(data);
        sessions.put(uuid, session);

        return session;
    }

    // ========================= GET =========================

    public PlayerSession get(UUID uuid) {
        return sessions.get(uuid);
    }

    public PlayerSession require(UUID uuid) {
        PlayerSession session = sessions.get(uuid);
        if (session == null) {
            throw new IllegalStateException("Session not loaded: " + uuid);
        }
        return session;
    }

    // ========================= DIRTY =========================

    /**
     * 【❅】 Mark session dirty → push to SaveQueue
     */
    public void markDirty(UUID uuid) {
        PlayerSession session = sessions.get(uuid);
        if (session == null) return;

        if (!session.isDirty()) {
            session.markDirty();
            saveQueue.enqueue(session);
        }
    }

    // ========================= SAVE =========================

    /**
     * 【❅】 Force save (rare use)
     */
    public void save(UUID uuid) {
        PlayerSession session = sessions.get(uuid);
        if (session == null) return;

        saveQueue.enqueue(session);
    }

    // ========================= UNLOAD =========================

    /**
     * 【❅】 Remove player session (on quit)
     */
    public void unload(UUID uuid) {
        PlayerSession session = sessions.remove(uuid);
        if (session == null) return;

        // 【❅】 Ensure last save
        saveQueue.enqueue(session);
    }

    // ========================= DEBUG =========================

    public int size() {
        return sessions.size();
    }
}
