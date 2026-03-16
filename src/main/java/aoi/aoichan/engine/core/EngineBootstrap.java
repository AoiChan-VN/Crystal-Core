package aoi.aoichan.engine.core;

import aoi.aoichan.engine.util.EngineLogger;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * EngineBootstrap
 *
 * Class chịu trách nhiệm khởi tạo toàn bộ engine runtime.
 *
 * Flow:
 * Plugin Enable
 *   ↓
 * EngineBootstrap
 *   ↓
 * Create Engine
 *   ↓
 * Start Engine Systems
 */
public class EngineBootstrap {

    private final JavaPlugin plugin;

    public EngineBootstrap(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public Engine boot() {

        EngineLogger.info("Bootstrapping engine...");

        Engine engine = new Engine(plugin);

        engine.start();

        EngineLogger.info("Engine bootstrap complete.");

        return engine;
    }
} 
