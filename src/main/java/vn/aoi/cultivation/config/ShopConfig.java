package vn.aoi.cultivation.config;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public final class ShopConfig {

    private final Map<String, ShopItem> shopItems =
            new LinkedHashMap<>();

    public ShopConfig(File file) {

        YamlConfiguration config =
                YamlConfiguration.loadConfiguration(file);

        ConfigurationSection section =
                config.getConfigurationSection("items");

        if (section == null) {
            throw new IllegalStateException(
                    "shops.yml missing section: items"
            );
        }

        for (String id : section.getKeys(false)) {

            ConfigurationSection itemSection =
                    section.getConfigurationSection(id);

            if (itemSection == null) {
                continue;
            }

            String materialName =
                    itemSection.getString("material");

            if (materialName == null) {
                throw new IllegalStateException(
                        "Missing material for shop item: " + id
                );
            }

            Material material =
                    Material.matchMaterial(materialName);

            if (material == null) {
                throw new IllegalStateException(
                        "Invalid material: " + materialName
                );
            }

            String displayName =
                    itemSection.getString(
                            "display-name",
                            material.name()
                    );

            int amount =
                    itemSection.getInt(
                            "amount",
                            1
                    );

            double buyPrice =
                    itemSection.getDouble(
                            "buy-price",
                            0D
                    );

            double sellPrice =
                    itemSection.getDouble(
                            "sell-price",
                            0D
                    );

            ShopItem shopItem =
                    new ShopItem(
                            id,
                            material,
                            displayName,
                            amount,
                            buyPrice,
                            sellPrice
                    );

            shopItems.put(id, shopItem);
        }
    }

    public Map<String, ShopItem> getShopItems() {
        return Collections.unmodifiableMap(shopItems);
    }

    public ShopItem getItem(String id) {
        return shopItems.get(id);
    }

    public boolean contains(String id) {
        return shopItems.containsKey(id);
    }

    public static final class ShopItem {

        private final String id;
        private final Material material;
        private final String displayName;
        private final int amount;
        private final double buyPrice;
        private final double sellPrice;

        public ShopItem(String id,
                        Material material,
                        String displayName,
                        int amount,
                        double buyPrice,
                        double sellPrice) {

            this.id = id;
            this.material = material;
            this.displayName = displayName;
            this.amount = amount;
            this.buyPrice = buyPrice;
            this.sellPrice = sellPrice;
        }

        public String getId() {
            return id;
        }

        public Material getMaterial() {
            return material;
        }

        public String getDisplayName() {
            return displayName;
        }

        public int getAmount() {
            return amount;
        }

        public double getBuyPrice() {
            return buyPrice;
        }

        public double getSellPrice() {
            return sellPrice;
        }
    }
}
