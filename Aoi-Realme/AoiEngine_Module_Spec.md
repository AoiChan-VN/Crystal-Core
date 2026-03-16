# AOIENGINE MODULE SYSTEM

This document describes how modules work inside AoiEngine.

Modules allow engine features to be separated into independent components.

---

# Module Concept

Each subsystem can be implemented as a module.

Examples:

StatModule
SkillModule
CombatModule
WorldModule

Modules are loaded dynamically by ModuleManager.

---

# Module Descriptor

Each module must include a descriptor file.

module.yml

Example:

name: AoiSkill
main: aoi.skill.AoiSkillModule
version: 1.0
depend:

* AoiEngine

---

# Module Lifecycle

Modules have lifecycle methods.

onLoad()

load configuration
register services

onEnable()

start tasks
register listeners

onDisable()

shutdown tasks
cleanup resources

---

# Module Dependencies

Modules may depend on other modules.

Example:

AoiSkill depends on AoiStats.

depend:

* AoiStats

Engine resolves dependencies automatically.

---

# Module Registration

Modules must be registered with ModuleManager.

Example:

moduleManager.register(new SkillModule())

---

# Module Isolation

Modules must not directly access internal engine state.

All interactions must use:

EngineAPI
ServiceRegistry

---

# Hot Module Reload

Future feature:

modules may support hot reload.

Example command:

/aoi module reload AoiSkill
 
