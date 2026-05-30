```txt
Cultivation/
├── build.gradle.kts
├── settings.gradle.kts
├── paper-plugin.yml
├── gradle.properties
├── README.md
│
└── src/
    └── main/
        ├── java/
        │   └── vn/
        │       └── aoi/
        │           └── cultivation/
        │               ├── CultivationPlugin.java
        │
        │               ├── bootstrap/
        │               │   ├── PluginBootstrap.java
        │               │   └── DependencyLoader.java
        │
        │               ├── core/
        │               │   ├── engine/
        │               │   │   ├── CultivationEngine.java
        │               │   │   ├── QiEngine.java
        │               │   │   └── BreakthroughEngine.java
        │               │   │
        │               │   ├── domain/
        │               │   │   ├── Realm.java
        │               │   │   ├── CultivationStage.java
        │               │   │   └── QiState.java (record)
        │               │   │
        │               │   ├── service/
        │               │   │   ├── PlayerCultivationService.java
        │               │   │   ├── BreakthroughService.java
        │               │   │   └── QiService.java
        │               │   │
        │               │   └── registry/
        │               │       ├── RealmRegistry.java
        │               │       └── SkillRegistry.java
        │
        │               ├── data/
        │               │   ├── repository/
        │               │   │   ├── PlayerCultivationRepository.java
        │               │   │   └── SqlRepository.java
        │               │   │
        │               │   ├── model/
        │               │   │   ├── PlayerCultivationData.java (record)
        │               │   │   └── QiSnapshot.java (record)
        │               │   │
        │               │   └── cache/
        │               │       ├── CultivationCache.java
        │               │       └── CacheEvictor.java
        │
        │               ├── command/
        │               │   ├── CultivationCommand.java
        │               │   ├── sub/
        │               │   │   ├── BreakthroughSubCommand.java
        │               │   │   ├── QiSubCommand.java
        │               │   │   └── StatsSubCommand.java
        │
        │               ├── listener/
        │               │   ├── PlayerJoinListener.java
        │               │   ├── PlayerQuitListener.java
        │               │   ├── CombatListener.java
        │               │   └── WorldProtectionListener.java
        │
        │               ├── task/
        │               │   ├── QiRegenerationTask.java
        │               │   ├── AuraPulseTask.java
        │               │   └── AsyncDataSaveTask.java
        │
        │               ├── util/
        │               │   ├── ThreadUtil.java
        │               │   ├── MiniMessageUtil.java
        │               │   └── ValidationUtil.java
        │
        │               └── exception/
        │                   ├── CultivationException.java
        │                   └── BreakthroughException.java
        │
        └── resources/
            ├── config.yml
            ├── messages.yml
            └── realms.yml
```
