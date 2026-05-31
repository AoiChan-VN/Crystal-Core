// ══════════════════════════════════════════════════════════════════════════════
// Cultivation — settings.gradle.kts
// Gradle settings for the single-module project.
//
// This file is evaluated BEFORE build.gradle.kts.
// Its primary jobs:
//   1. Declare where Gradle should look for plugins (pluginManagement)
//   2. Set the root project name (used in the output JAR filename)
// ══════════════════════════════════════════════════════════════════════════════

// ── PLUGIN MANAGEMENT ─────────────────────────────────────────────────────────
// Declares the repositories Gradle uses to RESOLVE plugin artifacts
// (i.e., the 'plugins { id(...) version(...) }' block in build.gradle.kts).
// This block must come first, before any other configuration.
pluginManagement {
    repositories {
        // The official Gradle Plugin Portal — resolves:
        //   com.github.johnrengelman.shadow
        gradlePluginPortal()

        // Maven Central as fallback
        mavenCentral()
    }
}

// ── ROOT PROJECT NAME ─────────────────────────────────────────────────────────
// Controls:
//   - The default artifact base name used in build.gradle.kts:
//       archiveFileName.set("${rootProject.name}-${project.version}.jar")
//       → produces "Cultivation-1.0.0-SNAPSHOT.jar"
//   - The IDE project display name
//
// KEEP THIS IN SYNC with the 'name' field in paper-plugin.yml.
rootProject.name = "Cultivation"
