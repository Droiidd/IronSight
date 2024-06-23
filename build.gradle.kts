import xyz.jpenilla.resourcefactory.bukkit.BukkitPluginYaml

plugins {
    java
    id("xyz.jpenilla.resource-factory-bukkit-convention") version "1.1.1" // Generates plugin.yml based on the Gradle config
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("xyz.jpenilla.run-paper") version "2.3.0" // Adds runServer and runMojangMappedServer tasks for testing
    id("com.diffplug.spotless") version "7.0.0.BETA1"
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
    compileOnly("org.spigotmc:spigot-api:1.20.4-R0.1-SNAPSHOT")
    implementation("com.mysql:mysql-connector-j:8.3.0")
    testImplementation("com.github.seeseemelk:MockBukkit-v1.20:3.80.0")
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.2")
}

configurations.testImplementation {
    exclude("io.papermc.paper", "paper-server")
}

tasks { //use paper solely for gradle convenience when testing
    runServer {
        minecraftVersion("1.20.4")
    }
}

tasks.named<Test>("test") {
    useJUnitPlatform()

    maxHeapSize = "1G"

    testLogging {
        events("passed")
    }
}

tasks.shadowJar {
    if (System.getenv("GITHUB_ACTIONS") != "true") {
        dependsOn("hook")
    }
}

val targetJavaVersion: Int = 21

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

spotless {
    java {
        importOrder()
        cleanthat()
        googleJavaFormat() // there's also eclipse(), prettier(), and clangFormat(), this just feels
        // best to me
        formatAnnotations()
    }
    kotlinGradle { ktfmt().googleStyle() }
}

tasks.register<Copy>("hook") {
    from(".github/hooks")
    into(".git/hooks")
    fileMode = 755
}

tasks.runServer { dependsOn("hook") }

bukkitPluginYaml {
    main = "droidco.west3.ironsight.IronSight"
    load = BukkitPluginYaml.PluginLoadOrder.STARTUP
    authors.add("Droidd")
    apiVersion = "1.20"
    commands {
        register("ironsight") { aliases = listOf("is") }
        register("stats")
        register("contract") { aliases = listOf("c") }
        register("give_common") { aliases = listOf("g_c") }
        register("masterlist") { aliases = listOf("list") }
        register("call") { aliases = listOf("horse") }
        register("gethorse")
        register("dropgold") { aliases = listOf("dm", "drop") }
        register("suicide") { aliases = listOf("sd") }
        register("play")
    }
}