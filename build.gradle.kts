plugins {
    java
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "vn.aoi"
version = "1.0.0-SNAPSHOT"
description = "Cultivation - Production Grade Tu Tiên Plugin"

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

    // Paper
    compileOnly("io.papermc.paper:paper-api:1.21.4-R0.1-SNAPSHOT")

    // Optional integrations
    compileOnly("me.clip:placeholderapi:2.11.6")
    compileOnly("com.github.MilkBowl:VaultAPI:1.7.1")

    // Database
    implementation("org.xerial:sqlite-jdbc:3.47.1.0")
    implementation("com.zaxxer:HikariCP:5.1.0")

    // Metrics
    implementation("org.bstats:bstats-bukkit:3.1.0")
}

tasks {

    compileJava {
        options.encoding = "UTF-8"
        options.release.set(21)
    }

    processResources {
        filesMatching("paper-plugin.yml") {
            expand(
                mapOf(
                    "version" to project.version
                )
            )
        }
    }

    shadowJar {

        archiveClassifier.set("")

        relocate(
            "org.sqlite",
            "vn.aoi.cultivation.libs.sqlite"
        )

        relocate(
            "com.zaxxer.hikari",
            "vn.aoi.cultivation.libs.hikari"
        )

        relocate(
            "org.bstats",
            "vn.aoi.cultivation.libs.bstats"
        )

        exclude("META-INF/*.SF")
        exclude("META-INF/*.DSA")
        exclude("META-INF/*.RSA")
        exclude("META-INF/NOTICE*")
        exclude("META-INF/LICENSE*")

        minimize {

            exclude(
                dependency(
                    "org.xerial:sqlite-jdbc:.*"
                )
            )
        }
    }

    build {
        dependsOn(shadowJar)
    }

    jar {
        enabled = false
    }
}
