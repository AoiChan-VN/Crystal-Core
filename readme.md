```txt
CultivationPlugin/
├── pom.xml
├── README.md
├── .github/
│   └── workflows/
│       └── build.yml
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── vn/
│   │   │       └── aoi/
│   │   │           └── cultivation/
│   │   │               ├── CultivationPlugin.java
│   │   │               │
│   │   │               ├── core/
│   │   │               │   ├── bootstrap/
│   │   │               │   │   └── PluginBootstrap.java
│   │   │               │   ├── scheduler/
│   │   │               │   │   ├── AsyncScheduler.java
│   │   │               │   │   └── SyncScheduler.java
│   │   │               │   ├── security/
│   │   │               │   │   ├── AntiDupeManager.java
│   │   │               │   │   ├── TransactionGuard.java
│   │   │               │   │   └── ClickCooldownManager.java
│   │   │               │   ├── cache/
│   │   │               │   │   ├── PlayerCache.java
│   │   │               │   │   └── DataCacheManager.java
│   │   │               │   └── util/
│   │   │               │       ├── UUIDUtil.java
│   │   │               │       ├── ItemUtil.java
│   │   │               │       └── ThreadUtil.java
│   │   │               │
│   │   │               ├── config/
│   │   │               │   ├── ConfigManager.java
│   │   │               │   ├── MessageConfig.java
│   │   │               │   ├── RealmConfig.java
│   │   │               │   ├── ShopConfig.java
│   │   │               │   ├── MobDropConfig.java
│   │   │               │   └── MobRewardConfig.java
│   │   │               │
│   │   │               ├── listener/
│   │   │               │   ├── InventoryClickListener.java
│   │   │               │   ├── InventoryCloseListener.java
│   │   │               │   ├── PlayerJoinListener.java
│   │   │               │   └── MobKillListener.java
│   │   │               │
│   │   │               ├── gui/
│   │   │               │   ├── holder/
│   │   │               │   │   └── CustomInventoryHolder.java
│   │   │               │   ├── menu/
│   │   │               │   │   ├── MainMenu.java
│   │   │               │   │   └── ShopMenu.java
│   │   │               │   └── builder/
│   │   │               │       └── MenuBuilder.java
│   │   │               │
│   │   │               ├── economy/
│   │   │               │   ├── CultivationEconomy.java
│   │   │               │   └── TransactionService.java
│   │   │               │
│   │   │               ├── realm/
│   │   │               │   ├── RealmManager.java
│   │   │               │   └── RealmProgression.java
│   │   │               │
│   │   │               └── service/
│   │   │                   ├── DropService.java
│   │   │                   ├── RewardService.java
│   │   │                   └── ShopService.java
│   │   │
│   │   └── resources/
│   │       ├── plugin.yml
│   │       ├── config.yml
│   │       ├── messages.yml
│   │       ├── realms.yml
│   │       ├── mob_rewards.yml
│   │       ├── mob_drops.yml
│   │       ├── shops.yml
│   │       │
│   │       └── menus/
│   │           ├── main_menu.yml
│   │           └── shop_menu.yml
│
└── target/
```
