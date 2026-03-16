# AOIENGINE COMBAT FORMULAS

This document defines combat calculations.

---

## Base Damage

Damage =

(BaseAttack + Strength × 1.5)
× SkillMultiplier
× CritMultiplier
− DefenseReduction

---

## Critical Chance

critChance = agility × 0.02

Example:

Agility 50
Crit chance = 1.0 (100%)

---

## Critical Damage

critDamage = 1.5 + (strength × 0.01)

---

## Defense Reduction

damageReduction = defense / (defense + 100)

---

## Final Damage

finalDamage = baseDamage × (1 − damageReduction)

---

## Dodge Chance

dodgeChance = agility × 0.01

---

## Mana Cost

manaCost = skillBaseMana × skillLevelMultiplier

---

## Cooldown Formula

cooldown = baseCooldown − (intelligence × 0.02)

Minimum cooldown = 1 second.
 
