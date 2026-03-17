package aoi.aoichan.api;

import aoi.aoichan.bootstrap.EngineBootstrap;
import aoi.aoichan.core.EngineLifecycle;
import aoi.aoichan.service.ServiceRegistry;

/**
 * Public API để plugin khác gọi vào
 */
public class EngineAPI {

    private static ServiceRegistry registry;

    public static void init(EngineLifecycle lifecycle) {
        registry = lifecycle.getServiceRegistry();
    }

    public static <T> T getService(Class<T> clazz) {
        if (registry == null) {
            throw new IllegalStateException("EngineAPI not initialized");
        }
        return registry.get(clazz);
    }
} 
