package aoichan.crystal.storage;

import aoichan.crystal.api.GemData;

import java.util.UUID;

// [!] Code: Storage Provider Interface
public interface StorageProvider {

    void initialize();

    GemData load(UUID uuid);

    void save(UUID uuid);

    void saveSync(UUID uuid);

    void shutdown();
}
