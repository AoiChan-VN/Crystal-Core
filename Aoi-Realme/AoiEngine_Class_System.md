# AOIENGINE CLASS SYSTEM

This document defines the **Character Class System** used by AoiEngine.

The class system controls:

* character classes
* stat scaling
* skill access
* class progression
* specialization paths

---

# Class Architecture

Core components:

ClassManager
ClassRegistry
ClassDefinition
ClassProgression
ClassSkillTree

Responsibilities:

ClassManager → manages player classes
ClassRegistry → loads class definitions
ClassDefinition → defines class data
ClassProgression → handles leveling and promotion
ClassSkillTree → manages skill unlocks

---

# Base Classes

Initial MMORPG classes:

Warrior
Mage
Archer
Assassin
Monk

Each class has unique stat scaling and skill access.

Example roles:

Warrior → tank / melee
Mage → magic damage
Archer → ranged DPS
Assassin → burst damage
Monk → hybrid fighter

---

# Class Definition Format

Classes are defined using YAML.

Example:

class:

id: warrior
name: Warrior
primaryStat: strength
secondaryStat: vitality
baseHealth: 120
baseMana: 40

---

# Stat Scaling

Each class scales differently with stats.

Example:

Warrior

strengthMultiplier: 1.8
vitalityMultiplier: 1.5
agilityMultiplier: 0.8
intelligenceMultiplier: 0.4

Mage

intelligenceMultiplier: 2.0
agilityMultiplier: 0.7
strengthMultiplier: 0.3

---

# Class Skills

Each class has exclusive skills.

Example:

Warrior skills:

Slash
Shield Bash
Berserk

Mage skills:

Fireball
Ice Spike
Arcane Burst

Skills are unlocked via level or skill tree.

---

# Skill Tree System

Each class has a skill tree.

Example structure:

Offense branch
Defense branch
Utility branch

Example Warrior tree:

Offense → heavy damage
Defense → tank abilities
Utility → mobility and buffs

Players spend skill points to unlock nodes.

---

# Class Progression

Classes can evolve into advanced classes.

Example progression:

Warrior

→ Knight
→ Berserker

Mage

→ Sorcerer
→ Elementalist

Archer

→ Ranger
→ Sniper

Assassin

→ Shadowblade
→ Phantom

Monk

→ Spirit Monk
→ Battle Monk

---

# Promotion Requirements

Example:

promotion:

level: 30
quest: class_trial
items: promotion_token

Players must complete requirements to evolve class.

---

# Class Bonuses

Each class provides passive bonuses.

Example Warrior bonuses:

+10% melee damage
+15% armor

Mage bonuses:

+20% mana regeneration
+15% spell damage

---

# Class Switching

Optional feature.

Players may change class using:

class reset token

This resets:

skills
class progression

---

# Class Data Storage

Stored in player profile:

classId
classLevel
skillPoints
unlockedSkills

Example:

player_class

uuid
class_id
class_level
skill_points

---

# Future Extensions

Possible upgrades:

dual classes
prestige classes
class mastery levels
ultimate abilities
 
