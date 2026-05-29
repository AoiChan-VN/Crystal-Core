package vn.aoi.cultivation.core.security;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class AntiDupeManager {

    /**
     * Lưu trạng thái snapshot inventory theo UUID
     * Dùng để phát hiện thay đổi bất thường khi đóng GUI
     */
    private final Map<UUID, ItemStack[]> inventorySnapshot = new ConcurrentHashMap<>();

    /**
     * Đánh dấu player đang trong phiên GUI giao dịch
     */
    private final Map<UUID, Boolean> activeSession = new ConcurrentHashMap<>();

    /**
     * Bắt đầu session giao dịch / GUI
     */
    public void startSession(Player player, Inventory inventory) {
        UUID uuid = player.getUniqueId();

        activeSession.put(uuid, true);
        inventorySnapshot.put(uuid, inventory.getContents().clone());
    }

    /**
     * Kết thúc session giao dịch
     */
    public void endSession(Player player) {
        UUID uuid = player.getUniqueId();

        activeSession.remove(uuid);
        inventorySnapshot.remove(uuid);
    }

    /**
     * Kiểm tra có đang trong session không
     */
    public boolean isInSession(Player player) {
        return activeSession.getOrDefault(player.getUniqueId(), false);
    }

    /**
     * Phát hiện thay đổi inventory bất thường (dupe attempt detection)
     */
    public boolean detectTamper(Player player, Inventory current) {
        UUID uuid = player.getUniqueId();

        ItemStack[] snapshot = inventorySnapshot.get(uuid);
        if (snapshot == null) return false;

        ItemStack[] now = current.getContents();

        if (snapshot.length != now.length) {
            return true; // inventory structure tampered
        }

        for (int i = 0; i < snapshot.length; i++) {

            ItemStack before = snapshot[i];
            ItemStack after = now[i];

            if (before == null && after == null) continue;

            if (before == null || after == null) {
                return true;
            }

            if (!before.isSimilar(after) || before.getAmount() != after.getAmount()) {
                return true;
            }
        }

        return false;
    }

    /**
     * Force rollback inventory về snapshot (anti-dupe recovery)
     */
    public void rollback(Player player, Inventory inventory) {
        UUID uuid = player.getUniqueId();

        ItemStack[] snapshot = inventorySnapshot.get(uuid);
        if (snapshot == null) return;

        inventory.setContents(snapshot.clone());
    }

    /**
     * Cleanup memory
     */
    public void clear(Player player) {
        UUID uuid = player.getUniqueId();
        inventorySnapshot.remove(uuid);
        activeSession.remove(uuid);
    }

    /**
     * Emergency wipe toàn bộ session (shutdown safety)
     */
    public void clearAll() {
        inventorySnapshot.clear();
        activeSession.clear();
    }
} 
