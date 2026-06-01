package vn.aoi.cultivation;

import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import vn.aoi.cultivation.database.DatabaseManager;
import vn.aoi.cultivation.manager.CultivatorManager;
import vn.aoi.cultivation.manager.MeditationManager;
import vn.aoi.cultivation.manager.TribulationManager;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class CultivationPlugin extends JavaPlugin {

    private DatabaseManager databaseManager;
    private CultivatorManager cultivatorManager;
    private MeditationManager meditationManager;
    private TribulationManager tribulationManager;

    @Override
    public void onEnable() {
        try {
            saveDefaultConfig();

            this.databaseManager = new DatabaseManager(this);
            this.databaseManager.initialize();

            this.cultivatorManager = new CultivatorManager(this, databaseManager);
            this.meditationManager = new MeditationManager(this, cultivatorManager);
            this.tribulationManager = new TribulationManager(this, cultivatorManager);

            getLogger().info("========================================");
            getLogger().info("Cultivation Plugin Enabled");
            getLogger().info("Target: Paper 1.21.4");
            getLogger().info("Java: 21");
            getLogger().info("========================================");
        } catch (Exception exception) {
            getLogger().log(
                    Level.SEVERE,
                    "Fatal startup failure. Disabling plugin.",
                    exception
            );

            getServer().getPluginManager().disablePlugin(this);
        }
    }

    @Override
    public void onDisable() {
        try {
            if (meditationManager != null) {
                meditationManager.shutdown();
            }

            if (tribulationManager != null) {
                tribulationManager.shutdown();
            }

            if (cultivatorManager != null) {
                cultivatorManager.shutdown();
            }

            if (databaseManager != null) {
                databaseManager.shutdown();
            }

            getLogger().info("Cultivation Plugin Disabled.");
        } catch (Exception exception) {
            getLogger().log(
                    Level.SEVERE,
                    "Error during plugin shutdown.",
                    exception
            );
        }
    }

    public @NotNull DatabaseManager getDatabaseManager() {
        return Objects.requireNonNull(
                databaseManager,
                "DatabaseManager not initialized"
        );
    }

    public @NotNull CultivatorManager getCultivatorManager() {
        return Objects.requireNonNull(
                cultivatorManager,
                "CultivatorManager not initialized"
        );
    }

    public @NotNull MeditationManager getMeditationManager() {
        return Objects.requireNonNull(
                meditationManager,
                "MeditationManager not initialized"
        );
    }

    public @NotNull TribulationManager getTribulationManager() {
        return Objects.requireNonNull(
                tribulationManager,
                "TribulationManager not initialized"
        );
    }

    public @NotNull Logger logger() {
        return getLogger();
    }
}
