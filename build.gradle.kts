import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

// ══════════════════════════════════════════════════════════════════════════════
// Cultivation — Tu Tiên Plugin
// Target: Paper 1.21.4 / Java 21 / Gradle 8.x
// ══════════════════════════════════════════════════════════════════════════════

plugins {
    java
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group       = "vn.aoi"
version     = "1.0.0-SNAPSHOT"
description = "Cultivation – Tu Tiên Plugin cho Minecraft Java Edition"

// ── JAVA TOOLCHAIN ────────────────────────────────────────────────────────────
// Forces Gradle to use (or download) a Java 21 JDK for compilation,
// regardless of the JDK running Gradle itself.
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

// ── REPOSITORIES ──────────────────────────────────────────────────────────────
repositories {
    mavenCentral()

    // Paper API + SNAPSHOT builds
    maven {
        name = "papermc"
        url  = uri("https://repo.papermc.io/repository/maven-public/")
    }

    // PlaceholderAPI
    maven {
        name = "placeholderapi"
        url  = uri("https://repo.extendedclip.com/content/repositories/placeholderapi/")
    }

    // Vault (hosted on JitPack)
    maven {
        name = "jitpack"
        url  = uri("https://jitpack.io")
    }

    // WorldGuard / WorldEdit (EngineHub)
    maven {
        name = "enginehub"
        url  = uri("https://maven.enginehub.org/repo/")
    }
}

// ── DEPENDENCIES ──────────────────────────────────────────────────────────────
dependencies {

    // ── SERVER-PROVIDED — compile-only; Paper ships these at runtime ─────────
    // DO NOT shade these; they will cause duplicate class errors on the server.
    compileOnly("io.papermc.paper:paper-api:1.21.4-R0.1-SNAPSHOT")
    compileOnly("me.clip:placeholderapi:2.11.6")
    compileOnly("com.github.MilkBowl:VaultAPI:1.7.1")
    // NOTE: Update WorldGuard version to match the one deployed on your server.
    //       7.0.9 is a known-stable API-compatible release. Upgrade freely.
    compileOnly("com.sk89q.worldguard:worldguard-bukkit:7.0.9")

    // ── SHADED — must be bundled and relocated in the output JAR ─────────────
    // Every entry here MUST have a corresponding 'relocate' call in shadowJar.
    implementation("org.xerial:sqlite-jdbc:3.47.1.0")   // SQLite JDBC driver
    implementation("com.zaxxer:HikariCP:5.1.0")          // JDBC connection pool
    implementation("org.bstats:bstats-bukkit:3.1.0")     // Anonymous plugin metrics
}

// ── TASKS ─────────────────────────────────────────────────────────────────────
tasks {

    // ── JAVA COMPILE ──────────────────────────────────────────────────────────
    withType<JavaCompile> {
        options.encoding = "UTF-8"
        options.release.set(21)               // Hard-target Java 21 bytecode
        options.compilerArgs.addAll(
            listOf(
                "-Xlint:unchecked",           // Flag unchecked generic casts
                "-Xlint:deprecation"          // Flag deprecated API usage
            )
        )
    }

    // ── PROCESS RESOURCES ─────────────────────────────────────────────────────
    // At build time, replaces ${version} and ${description} placeholders
    // inside paper-plugin.yml with the values declared above.
    processResources {
        val props = mapOf(
            "version"     to project.version.toString(),
            "description" to (project.description ?: "Cultivation Tu Tien Plugin")
        )
        inputs.properties(props)   // Invalidate cache when values change
        filteringCharset = "UTF-8"
        filesMatching("paper-plugin.yml") {
            expand(props)
        }
    }

    // ── SHADOW JAR ────────────────────────────────────────────────────────────
    named<ShadowJar>("shadowJar") {

        // Output filename: "Cultivation-1.0.0-SNAPSHOT.jar"
        // archiveClassifier = "" removes the default "-all" suffix.
        archiveClassifier.set("")
        archiveFileName.set("${rootProject.name}-${project.version}.jar")

        // ── RELOCATIONS ───────────────────────────────────────────────────────
        // Moves every shaded class to a sub-package under this plugin's root.
        //
        // WHY THIS IS CRITICAL:
        //   If ServerPlugin-A shades HikariCP at "com.zaxxer.hikari" and
        //   this plugin also shades it at the same path, only ONE version will
        //   be loaded — causing subtle version-mismatch bugs or NPEs.
        //   Relocation gives each plugin its own private copy.
        relocate("org.sqlite",        "vn.aoi.cultivation.libs.sqlite")
        relocate("org.xerial",        "vn.aoi.cultivation.libs.xerial")
        relocate("com.zaxxer.hikari", "vn.aoi.cultivation.libs.hikari")
        relocate("org.bstats",        "vn.aoi.cultivation.libs.bstats")

        // ── EXCLUSIONS ────────────────────────────────────────────────────────
        // JAR signing files become INVALID after shading (the digest no longer
        // matches the merged content). Keeping them causes SecurityException
        // on JVMs with strict JAR verification enabled.
        exclude("META-INF/*.SF")
        exclude("META-INF/*.DSA")
        exclude("META-INF/*.RSA")
        exclude("META-INF/MANIFEST.MF")
        exclude("META-INF/NOTICE*")
        exclude("META-INF/LICENSE*")
        exclude("META-INF/DEPENDENCIES")
        exclude("module-info.class")

        // ── MINIMIZE ──────────────────────────────────────────────────────────
        // Removes unused class files from shaded libraries to reduce JAR size.
        //
        // EXCEPTION — sqlite-jdbc MUST be excluded from minimization:
        //   The driver bundles OS-specific native binaries (.so / .dll / .dylib)
        //   loaded via System.loadLibrary() at runtime. If minimize removes
        //   classes that reference those binaries, the driver will fail to init.
        minimize {
            exclude(dependency("org.xerial:sqlite-jdbc:.*"))
        }
    }

    // 'gradle build' always delegates to the shadow jar
    named("build") {
        dependsOn("shadowJar")
    }

    // Disable the plain, non-shaded jar.
    // Only the shadow jar should ever be placed on the server.
    named("jar") {
        enabled = false
    }
}
