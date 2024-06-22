import xyz.jpenilla.resourcefactory.bukkit.BukkitPluginYaml

plugins {
    java
    id("xyz.jpenilla.resource-factory-bukkit-convention") version "1.1.1" // Generates plugin.yml based on the Gradle config
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("io.papermc.paperweight.userdev") version "1.7.1"
    id("xyz.jpenilla.run-paper") version "2.3.0" // Adds runServer and runMojangMappedServer tasks for testing
}

group = "droidco.west3"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven {
        name = "spigotmc-repo"
        url = uri("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    }
    maven {
        name = "Paper"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
}

dependencies {
    paperweight.paperDevBundle("1.20.4-R0.1-SNAPSHOT")
    implementation("com.mysql:mysql-connector-j:8.3.0")
}

val targetJavaVersion: Int = 17
java {
    val javaVersion = JavaVersion.toVersion(targetJavaVersion)
    sourceCompatibility = javaVersion
    targetCompatibility = javaVersion
    if (JavaVersion.current() < javaVersion) {
        toolchain.languageVersion = JavaLanguageVersion.of(targetJavaVersion)
    }
}

tasks.withType<JavaCompile> {
    if (targetJavaVersion >= 10 || JavaVersion.current().isJava10Compatible) {
        options.release.set(targetJavaVersion)
    }
}

bukkitPluginYaml {
    main = "droidco.west3.ironsight.IronSight"
    load = BukkitPluginYaml.PluginLoadOrder.STARTUP
    authors.add("Droidd")
    apiVersion = "1.20"
    commands {
        register("ironsight") {
            aliases = listOf("is")
        }
        register("stats")
        register("contract") {
            aliases = listOf("c")
        }
        register("give_common") {
            aliases = listOf("g_c")
        }
        register("masterlist") {
            aliases = listOf("list")
        }
        register("call") {
            aliases = listOf("horse")
        }
        register("gethorse")
        register("dropgold") {
            aliases = listOf("dm", "drop")
        }
        register("suicide") {
            aliases = listOf("sd")
        }
    }
}