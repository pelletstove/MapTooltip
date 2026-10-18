pluginManagement {
    repositories {
        mavenLocal()
        mavenCentral()
        gradlePluginPortal()
        maven("https://maven.fabricmc.net/") { name = "Fabric" }
        maven("https://maven.neoforged.net/releases/") { name = "Neoforged" }
        maven("https://maven.kikugie.dev/snapshots") { name = "KikuGie Snapshots" }
    }

    plugins {
        id("dev.kikugie.fletching-table") version "0.1.0-alpha.22"
    }
}

plugins {
    id("dev.kikugie.stonecutter") version "0.9.1-beta.2"
}

stonecutter {
    create(rootProject) {
        fun match(version: String, vararg loaders: String) = loaders.forEach {
            if (it == "fabric" && stonecutter.eval(version, ">1.21.11")) {
                version("$version-$it", version).buildscript = "build.fabric_noremap.gradle.kts"
            } else {
                version("$version-$it", version).buildscript = "build.$it.gradle.kts"
            }
        }
        // 26.2 branch: only the Fabric 26.2 node is built
        match("26.2", "fabric")
        vcsVersion = "26.2-fabric"
    }
}

rootProject.name = "Map Tooltip"