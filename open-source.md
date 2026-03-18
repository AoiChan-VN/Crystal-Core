# 🌌 AoiCore - Architecture Design (Premium Edition)

## 🎯 Mục tiêu

AoiCore là core engine cho server Minecraft (Spigot/Paper) với mục tiêu:

* Hiệu năng cao (no lag, không block main thread)
* Dễ mở rộng (plug-and-play modules)
* Không chứa gameplay
* Là nền tảng cho hệ plugin:

  * AoiWorld
  * AoiCrystal
  * AoiClass
  * AoiCrystal

---

* Tuyệt đối không truy cập DB trên main thread
* Core không phụ thuộc plugin con


## 🚀 Mở rộng plugin
👉 Không cần sửa Core

---

## 🧱 Tiêu chuẩn "Premium"

* Không block main thread
* không spam load, save,...
* Không phụ thuộc gameplay
* Reload không restart

---

## ❌ Những điều cần tránh

* Hardcode data vào PlayerData 
* Query DB liên tục
* Sync IO trên main thread
* Coupling giữa plugins
---
 
## Quan trọng:
• No Code test, lỗi, bug ẩn
• File + Code Comment // 【❅】:
