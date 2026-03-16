package aoi.aoichan.engine.service;

import aoi.aoichan.engine.util.EngineLogger;

import java.util.HashMap;
import java.util.Map;

/**
 * ServiceManager
 *
 * Quản lý tất cả services trong engine.
 *
 * Service = hệ thống global
 * ví dụ:
 * - DatabaseService
 * - PlayerDataService
 * - EconomyService
 */
public class ServiceManager {

    private final Map<Class<?>, Object> services = new HashMap<>();

    public void initialize() {
        EngineLogger.info("ServiceManager initialized.");
    }

    public void shutdown() {

        EngineLogger.info("Shutting down services...");

        services.clear();
    }

    public <T> void register(Class<T> clazz, T service) {
        services.put(clazz, service);
    }

    public <T> T get(Class<T> clazz) {
        return clazz.cast(services.get(clazz));
    }

} 
