# 🌌 AoiCore - Architecture Design (Refined Premium Edition)

## 🎯 Mục tiêu

AoiCore là core engine cho server Minecraft (Spigot/Paper) với mục tiêu:

* Hiệu năng cao (no lag, không block main thread)
* Dễ mở rộng (plug-and-play modules)
* Không chứa gameplay
* Là nền tảng cho hệ plugin:

  * AoiWorld
  * AoiCrystal
  * AoiClass

---

## 🧠 Triết lý thiết kế

* RAM = dữ liệu runtime
* Database = lưu trữ lâu dài
* Tuyệt đối không truy cập DB trên main thread
* Cache-first architecture
* Core không phụ thuộc plugin con

---

## 🧩 Kiến trúc tổng thể

```
AoiCore
├── AoiMain
├── core/
│   ├── CoreAPI
│   ├── CoreProvider
│
├── player/
│   ├── PlayerData
│   ├── PlayerService
│
├── data/
│   ├── DataContainer
│   ├── DataRegistry
│   ├── DataKey (type-safe)
│
├── database/
│   ├── Database
│   ├── DataRepository
│
├── system/
│   ├── Scheduler
│   ├── Config
│
├── hook/
│   ├── CoreHook
│
├── event/
│   ├── PlayerDataLoadEvent
│   ├── PlayerDataSaveEvent
│
├── util/
```

---

## 🧍 PlayerData Design

```java
class PlayerData {
    UUID uuid;
    Map<String, DataContainer> containers;
    boolean dirty;
}
```

### Giải thích

* `containers`: nơi plugin lưu data riêng
* `dirty`: đánh dấu cần save

---

## 📦 DataContainer System (Improved)

```java
class DataContainer {
    private Map<String, Object> data;

    public int getInt(String key);
    public String getString(String key);
    public <T> T get(String key, Class<T> type);

    public void set(String key, Object value);
}
```

### 🔐 Type-safe nâng cao (khuyến nghị)

```java
class DataKey<T> {
    private final String key;
    private final Class<T> type;
}
```

```java
DataKey<Integer> LEVEL = new DataKey<>("level", Integer.class);
int level = container.get(LEVEL);
```

### Ví dụ dữ liệu

```yaml
AoiClass
key: "class"
data:
  level: 10
  exp: 2500

AoiCrystal
key: "crystal"
data:
  slots: 3
  gems: [...]

AoiWorld
key: "world"
data:
  bossDamage: 5000
```

---

## 🔌 Hook System

```java
interface CoreHook {
    String getKey();

    DataContainer createDefault(UUID uuid);

    default void onLoad(PlayerData data) {}
    default void onSave(PlayerData data) {}
}
```

### Cơ chế hoạt động

Khi player load:

* Core gọi tất cả hook
* Tạo container mặc định
* Attach vào PlayerData

---

## ⚡ Player Flow

### Join

* Async load từ DB
* Deserialize JSON
* Tạo PlayerData
* Attach containers từ hooks
* Đưa vào cache

### Runtime

* Plugin gọi API
* Lấy PlayerData từ cache
* Xử lý hoàn toàn trên RAM

### Quit

* Mark dirty
* Save async
* Remove khỏi cache

---

## 🗃️ Database Design

### Format JSON

```json
{
  "uuid": "player-uuid",
  "version": 1,
  "data": {
    "class": {...},
    "crystal": {...},
    "world": {...}
  }
}
```

### Ưu điểm

* Không cần migration schema
* Dễ mở rộng
* Linh hoạt plugin

---

## ⚙️ Cache System

```java
Map<UUID, PlayerData> cache;
```

### Nguyên tắc

* Runtime = cache
* DB chỉ dùng load/save

### Bổ sung

* Remove khi player quit
* Có thể thêm timeout eviction

---

## 🔄 Save System

### Cách hoạt động

* Khi data thay đổi → set dirty
* Scheduler chạy mỗi X giây
* Batch save các player dirty

### Bổ sung an toàn

* Save ngay khi player quit
* Flush toàn bộ khi server shutdown

```java
void flushAll();
```

---

## ⏱️ Scheduler System

Wrapper cho BukkitScheduler:

```java
runAsync();
runSync();
runLater();
runTimer();
```

---

## ⚙️ Config System

* Load config.yml vào RAM
* Có thể reload bằng command
* Không cần restart server

---

## 📡 API Usage

```java
CoreAPI api = CoreProvider.get();

PlayerData data = api.getPlayer(uuid);
DataContainer container = data.get("class");

int level = container.getInt("level");
container.set("level", level + 1);
```

---

## 📡 Event System (New)

```java
PlayerDataLoadEvent
PlayerDataSaveEvent
```

→ Cho phép plugin hook vào lifecycle

---

## 🚀 Mở rộng plugin

Plugin mới chỉ cần:

1. Implement CoreHook
2. Register hook
3. Dùng DataContainer

👉 Không cần sửa Core

---

## 🧱 Tiêu chuẩn "Premium"

* Không block main thread
* Async DB 100%
* Cache-first
* Dirty save system
* Type-safe data
* Event-driven
* Không phụ thuộc gameplay
* Reload không restart

---

## ❌ Những điều cần tránh

* Hardcode data vào PlayerData
* Query DB liên tục
* Sync IO trên main thread
* Coupling giữa plugins

---

## 🧪 Hướng nâng cấp tương lai

* Async write queue
* Metrics (cache size, DB latency)
* Module system
* Binary serialization (MessagePack/BSON)

---

## 🎯 Kết luận

AoiCore không chỉ là một plugin core.

Nó là nền tảng backend cho toàn bộ hệ sinh thái plugin:

* Mở rộng dễ dàng
* Hiệu năng cao
* Thiết kế sạch

👉 Sẵn sàng cho production server quy mô lớn 🚀
 
