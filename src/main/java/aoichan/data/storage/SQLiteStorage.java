package aoichan.data.storage;

import aoichan.data.model.PlayerData;

import java.util.HashMap;
import java.util.UUID;

public class SQLiteStorage implements Storage {

    private final HashMap<UUID, PlayerData> fakeDB = new HashMap<>();

    @Override
    public PlayerData load(UUID uuid) {
        return fakeDB.computeIfAbsent(uuid, PlayerData::new);
    }

    @Override
    public void save(PlayerData data) {
        fakeDB.put(data.getUuid(), data);
    }
} 
