```txt
Cultivation/
├── src/main/java/vn/aoi/cultivation/
│
│   ├── CultivationPlugin.java
│
│   ├── bootstrap/
│   │   ├── PluginBootstrap.java
│   │   ├── DependencyLoader.java
│   │
│   ├── core/
│   │   ├── CultivationCore.java
│   │   ├── CultivationManager.java
│   │   ├── LifecycleManager.java
│   │
│   ├── player/
│   │   ├── CultivationPlayer.java (record)
│   │   ├── PlayerCultivationData.java
│   │   ├── CultivationCache.java
│   │   ├── PlayerSessionManager.java
│   │
│   ├── realm/
│   │   ├── RealmType.java (sealed interface)
│   │   ├── MortalRealm.java
│   │   ├── FoundationRealm.java
│   │   ├── CoreRealm.java
│   │   ├── NascentRealm.java
│   │
│   ├── qi/
│   │   ├── QiSystem.java
│   │   ├── QiRegenTask.java
│   │   ├── QiStorage.java (record)
│   │
│   ├── combat/
│   │   ├── CultivationDamageEngine.java
│   │   ├── CriticalStrikeHandler.java
│   │   ├── SkillPipeline.java
│   │
│   ├── item/
│   │   ├── CultivationItemFactory.java
│   │   ├── ItemPDCKeys.java
│   │   ├── ArtifactRegistry.java
│   │
│   ├── command/
│   │   ├── CultivationCommand.java
│   │   ├── sub/
│   │   │   ├── QiCommand.java
│   │   │   ├── RealmCommand.java
│   │   │   ├── DebugCommand.java
│   │
│   ├── listener/
│   │   ├── PlayerJoinListener.java
│   │   ├── PlayerQuitListener.java
│   │   ├── CombatListener.java
│   │   ├── ItemListener.java
│   │
│   ├── storage/
│   │   ├── DataRepository.java
│   │   ├── SQLiteStorage.java
│   │   ├── StorageService.java
│   │
│   ├── async/
│   │   ├── AsyncBridge.java
│   │   ├── TaskScheduler.java
│   │
│   ├── util/
│   │   ├── UUIDUtil.java
│   │   ├── Validate.java
│   │   ├── MiniMessageUtil.java
│   │
│   └── exception/
│       ├── CultivationException.java
│       ├── DataCorruptionException.java
│
├── src/main/resources/
│   ├── paper-plugin.yml
│   ├── config.yml
│   ├── messages.yml
│   ├── artifacts.yml
│
└── build.gradle.kts
```
