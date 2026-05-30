plugins {
    java
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group   = "vn.aoi"
version = "1.0.0-SNAPSHOT"
description = "Cultivation - Tu Tiên Plugin"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://repo.extendedclip.com/content/repositories/placeholderapi/")
    maven("https://jitpack.io")
}

dependencies {
    // PROVIDED — server ships these
    compileOnly("io.papermc.paper:paper-api:1.21.4-R0.1-SNAPSHOT")
    compileOnly("me.clip:placeholderapi:2.11.6")
    compileOnly("com.github.MilkBowl:VaultAPI:1.7.1")

    // SHADED — must be relocated to avoid conflicts
    implementation("org.xerial:sqlite-jdbc:3.47.1.0")
    implementation("com.zaxxer:HikariCP:5.1.0")
    implementation("org.bstats:bstats-bukkit:3.1.0")
}

tasks {
    compileJava {
        options.encoding    = "UTF-8"
        options.release.set(21)
    }

    processResources {
        filesMatching("paper-plugin.yml") {
            expand("version" to project.version)
        }
    }

    shadowJar {
        archiveClassifier.set("")

        // CRITICAL relocations — prevents classpath conflicts with other plugins
        relocate("org.sqlite",        "vn.aoi.cultivation.libs.sqlite")
        relocate("com.zaxxer.hikari", "vn.aoi.cultivation.libs.hikari")
        relocate("org.bstats",        "vn.aoi.cultivation.libs.bstats")

        // Strip signing metadata to prevent SecurityException
        exclude("META-INF/*.SF", "META-INF/*.DSA", "META-INF/*.RSA")
        exclude("META-INF/NOTICE*", "META-INF/LICENSE*")

        // Minimize jar size — but preserve native SQLite binary
        minimize {
            exclude(dependency("org.xerial:sqlite-jdbc:.*"))
        }
    }

    build { dependsOn(shadowJar) }

    // Disable plain jar; always produce shadow jar only
    jar { enabled = false }
}
