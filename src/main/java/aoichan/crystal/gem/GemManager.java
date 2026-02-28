package aoichan.crystal.gem;

import aoidev.crystal.storage.StorageAdapter;
import org.bukkit.plugin.Plugin;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.CompletableFuture;

public class GemManager {

    private final Plugin plugin;
    private final StorageAdapter storage;
    private final Executor executor;

    private final Map<UUID, Gem> gems = new ConcurrentHashMap<>();

    public GemManager(Plugin plugin, StorageAdapter storage, Executor executor) {
        this.plugin = plugin;
        this.storage = storage;
        this.executor = executor;
    }

    public void loadAll() {
        storage.loadAll().thenAccept(list -> {
            for (Gem g : list) {
                gems.put(g.getId(), g);
            }
            plugin.getLogger().info("Loaded " + gems.size() + " gems.");
        });
    }

    public CompletableFuture<Void> create(String type, int level) {
        Gem gem = new Gem(UUID.randomUUID(), type, level);
        gems.put(gem.getId(), gem);
        return storage.save(gem);
    }

    public Optional<Gem> get(UUID id) {
        return Optional.ofNullable(gems.get(id));
    }

    public Collection<Gem> getAll() {
        return Collections.unmodifiableCollection(gems.values());
    }

    public CompletableFuture<Void> delete(UUID id) {
        gems.remove(id);
        return storage.delete(id);
    }
}
