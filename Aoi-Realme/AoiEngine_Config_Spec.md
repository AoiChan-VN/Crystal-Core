# AOIENGINE CONFIGURATION SPECIFICATION

This document defines how configuration files are structured.

Configs are located in:

plugins/AoiEngine/config/

---

# Main Config

config.yml

Example:

database:
type: mysql

combat:
critMultiplier: 1.5

world:
scaling: true

---

# Sub Config Files

stats.yml

Defines stat scaling.

skills.yml

Defines skill settings.

world.yml

Defines region and mob scaling.

combat.yml

Defines combat formulas.

---

# Config Reload

Engine supports hot reload.

Command:

/aoi reload

Reloads:

stats
skills
combat
world

No server restart required.

---

# Config Validation

All configs must pass validation during load.

Invalid values will produce errors in console.

Example checks:

negative values
missing fields
invalid types

---

# Config Access

Plugins must access config through ConfigManager.

Example:

ConfigManager.get("combat.critMultiplier")

---

# Best Practices

Keep configs readable.

Avoid extremely large config files.

Split configs when necessary.
 
