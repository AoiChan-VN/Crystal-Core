package vn.aoi.cultivation.gui.holder;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class CustomInventoryHolder implements InventoryHolder {

    /**
     * ID nội bộ để phân loại GUI
     * Không phụ thuộc vào Inventory Title (anti spoof)
     */
    private final String guiId;

    /**
     * Inventory gốc được gắn vào holder
     */
    private Inventory inventory;

    public CustomInventoryHolder(String guiId) {
        this.guiId = guiId;
    }

    /**
     * Trả về ID GUI nội bộ
     */
    public String getGuiId() {
        return guiId;
    }

    /**
     * Gắn inventory vào holder
     */
    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    /**
     * Bukkit required method
     */
    @Override
    public Inventory getInventory() {
        return inventory;
    }
} 
