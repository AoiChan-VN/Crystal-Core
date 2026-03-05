package aoichan.crystal.core.cache;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

// [!] Code: Dirty Player Tracking System
public class DirtyTracker {

    private final Set<UUID> dirtyPlayers = ConcurrentHashMap.newKeySet();

    public void markDirty(UUID uuid) {
        dirtyPlayers.add(uuid);
    }

    public Set<UUID> getDirtyPlayers() {
        return dirtyPlayers;
    }

    public void clear() {
        dirtyPlayers.clear();
    }

    public boolean isEmpty() {
        return dirtyPlayers.isEmpty();
    }
}
