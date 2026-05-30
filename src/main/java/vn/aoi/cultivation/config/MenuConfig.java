package vn.aoi.cultivation.config;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class MenuConfig {

    private final String title;
    private final int size;

    private final FillConfig fillConfig;

    private final Map<String, MenuItemConfig> items;
    private final Map<String, ActionConfig> actions;

    public MenuConfig(File file) {

        YamlConfiguration config =
                YamlConfiguration.loadConfiguration(file);

        this.title =
                config.getString(
                        "title",
                        "Menu"
                );

        this.size =
                config.getInt(
                        "size",
                        54
                );

        this.fillConfig =
                loadFillConfig(config);

        this.items =
                loadItems(config);

        this.actions =
                loadActions(config);
    }

    private FillConfig loadFillConfig(
            YamlConfiguration config
    ) {

        ConfigurationSection section =
                config.getConfigurationSection(
                        "fill"
                );

        if (section == null) {

            return new FillConfig(
                    false,
                    Material.BLACK_STAINED_GLASS_PANE,
                    " ",
                    Collections.emptyList()
            );
        }

        Material material =
                Material.matchMaterial(
                        section.getString(
                                "material",
                                "BLACK_STAINED_GLASS_PANE"
                        )
                );

        if (material == null) {
            material = Material.BLACK_STAINED_GLASS_PANE;
        }

        return new FillConfig(
                section.getBoolean(
                        "enabled",
                        true
                ),
                material,
                section.getString(
                        "display-name",
                        " "
                ),
                section.getIntegerList(
                        "slots"
                )
        );
    }

    private Map<String, MenuItemConfig> loadItems(
            YamlConfiguration config
    ) {

        Map<String, MenuItemConfig> result =
                new LinkedHashMap<>();

        ConfigurationSection itemsSection =
                config.getConfigurationSection(
                        "items"
                );

        if (itemsSection == null) {
            itemsSection =
                    config.getConfigurationSection(
                            "buttons"
                    );
        }

        if (itemsSection == null) {
            return result;
        }

        for (String id : itemsSection.getKeys(false)) {

            ConfigurationSection item =
                    itemsSection.getConfigurationSection(
                            id
                    );

            if (item == null) {
                continue;
            }

            Material material =
                    Material.matchMaterial(
                            item.getString(
                                    "material",
                                    "STONE"
                            )
                    );

            if (material == null) {
                material = Material.STONE;
            }

            MenuItemConfig menuItem =
                    new MenuItemConfig(
                            id,
                            item.getInt("slot"),
                            material,
                            item.getInt(
                                    "amount",
                                    1
                            ),
                            item.getString(
                                    "display-name",
                                    id
                            ),
                            item.getStringList(
                                    "lore"
                            ),
                            item.getString(
                                    "action",
                                    ""
                            )
                    );

            result.put(
                    id,
                    menuItem
            );
        }

        return result;
    }

    private Map<String, ActionConfig> loadActions(
            YamlConfiguration config
    ) {

        Map<String, ActionConfig> result =
                new LinkedHashMap<>();

        ConfigurationSection section =
                config.getConfigurationSection(
                        "actions"
                );

        if (section == null) {
            return result;
        }

        for (String key : section.getKeys(false)) {

            ConfigurationSection action =
                    section.getConfigurationSection(
                            key
                    );

            if (action == null) {
                continue;
            }

            result.put(
                    key,
                    new ActionConfig(
                            key,
                            action.getString(
                                    "type",
                                    ""
                            )
                    )
            );
        }

        return result;
    }

    public String getTitle() {
        return title;
    }

    public int getSize() {
        return size;
    }

    public FillConfig getFillConfig() {
        return fillConfig;
    }

    public Map<String, MenuItemConfig> getItems() {
        return Collections.unmodifiableMap(
                items
        );
    }

    public Map<String, ActionConfig> getActions() {
        return Collections.unmodifiableMap(
                actions
        );
    }

    public static final class FillConfig {

        private final boolean enabled;
        private final Material material;
        private final String displayName;
        private final List<Integer> slots;

        public FillConfig(
                boolean enabled,
                Material material,
                String displayName,
                List<Integer> slots
        ) {

            this.enabled = enabled;
            this.material = material;
            this.displayName = displayName;
            this.slots = slots;
        }

        public boolean isEnabled() {
            return enabled;
        }

        public Material getMaterial() {
            return material;
        }

        public String getDisplayName() {
            return displayName;
        }

        public List<Integer> getSlots() {
            return slots;
        }
    }

    public static final class MenuItemConfig {

        private final String id;
        private final int slot;
        private final Material material;
        private final int amount;
        private final String displayName;
        private final List<String> lore;
        private final String action;

        public MenuItemConfig(
                String id,
                int slot,
                Material material,
                int amount,
                String displayName,
                List<String> lore,
                String action
        ) {

            this.id = id;
            this.slot = slot;
            this.material = material;
            this.amount = amount;
            this.displayName = displayName;
            this.lore = lore;
            this.action = action;
        }

        public String getId() {
            return id;
        }

        public int getSlot() {
            return slot;
        }

        public Material getMaterial() {
            return material;
        }

        public int getAmount() {
            return amount;
        }

        public String getDisplayName() {
            return displayName;
        }

        public List<String> getLore() {
            return lore;
        }

        public String getAction() {
            return action;
        }
    }

    public static final class ActionConfig {

        private final String id;
        private final String type;

        public ActionConfig(
                String id,
                String type
        ) {

            this.id = id;
            this.type = type;
        }

        public String getId() {
            return id;
        }

        public String getType() {
            return type;
        }
    }
} 
