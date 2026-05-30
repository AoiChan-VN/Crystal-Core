plugins {
    java
    id("io.freefair.lombok") version "8.14.2"
}

group = "vn.aoi"
version = "1.0.0"

repositories {
    mavenCentral()

    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://repo.extendedclip.com/content/repositories/placeholderapi/")
    maven("https://jitpack.io")
    maven("https://repo.aikar.co/content/groups/aikar/")
    maven("https://mvn.lumine.io/repository/maven-public/")
    maven("https://maven.enginehub.org/repo/")
}

dependencies {

    // Paper
    compileOnly("io.papermc.paper:paper-api:1.21.8-R0.1-SNAPSHOT")

    // Adventure
    implementation("net.kyori:adventure-api:4.24.0")
    implementation("net.kyori:adventure-text-minimessage:4.24.0")

    // Database
    implementation("com.zaxxer:HikariCP:7.0.2")
    implementation("org.xerial:sqlite-jdbc:3.50.3.0")
    implementation("com.mysql:mysql-connector-j:9.4.0")

    // Cache
    implementation("com.github.ben-manes.caffeine:caffeine:3.2.3")

    // ACF
    implementation("co.aikar:acf-paper:0.5.1-SNAPSHOT")

    // Hooks
    compileOnly("me.clip:placeholderapi:2.11.6")
    compileOnly("com.github.MilkBowl:VaultAPI:1.7")
    compileOnly("io.lumine:Mythic-Dist:5.10.0")

    // WorldGuard
    compileOnly("com.sk89q.worldguard:worldguard-bukkit:7.0.13")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

tasks {

    compileJava {
        options.encoding = "UTF-8"
    }

    processResources {
        filteringCharset = "UTF-8"
    }

    jar {
        archiveBaseName.set("Cultivation")
    }
} 
