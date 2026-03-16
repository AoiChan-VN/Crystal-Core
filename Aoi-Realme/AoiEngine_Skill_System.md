# AOIENGINE SKILL SYSTEM

This document defines the **Skill Engine architecture** used by AoiEngine.

The skill system is responsible for:

* skill definitions
* skill execution
* cooldown management
* mana cost
* scaling formulas
* event dispatch

---

# Skill Architecture

Skill system components:

SkillManager
SkillExecutor
SkillContext
SkillCooldownManager
SkillRegistry

Responsibilities:

SkillManager → loads skills
SkillExecutor → executes skills
SkillContext → runtime data
CooldownManager → cooldown tracking

---

# Skill File Format

Skills are defined using YAML files.

Example:

skill:

id: fireball
name: Fireball
mana: 20
cooldown: 5
damage: 45
scaling: intelligence

---

# Skill Properties

Supported properties:

id
name
mana
cooldown
damage
scaling
range
castTime
effects

Example:

skill:

id: ice_spike
name: Ice Spike
mana: 25
cooldown: 6
damage: 50
scaling: intelligence
range: 12
castTime: 1.5

---

# Skill Scaling

Skill damage scales with player stats.

Example:

damage = baseDamage + (statScaling × statValue)

Example fireball:

damage = 45 + (intelligence × 1.3)

---

# Skill Execution Pipeline

Skill execution flow:

SkillCast
→ ManaCheck
→ CooldownCheck
→ TargetValidation
→ SkillExecutor
→ DamageEngine
→ EventDispatch

---

# Skill Context

Runtime object:

SkillContext contains:

caster
target
skillId
manaCost
cooldown
damage

Example:

SkillContext

caster = player
target = entity
skillId = fireball

---

# Cooldown System

Cooldowns tracked per player.

Example structure:

cooldowns

playerUUID
skillId
timestamp

Cooldown calculation:

nextCast = lastCast + cooldownSeconds

---

# Skill Effects

Skills may apply effects.

Supported effects:

damage
heal
buff
debuff
knockback
teleport

Example:

effects:

* type: burn
  duration: 5
  damage: 3

---

# Skill Events

Events triggered:

SkillCastEvent
SkillHitEvent
SkillCooldownStartEvent

Plugins can listen to these events.

---

# Future Extensions

Possible upgrades:

combo skills
skill trees
skill upgrades
skill runes
channeling skills
 
