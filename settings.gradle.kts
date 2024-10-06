pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
    versionCatalogs {
        create("androidLibs") {
            from(files("gradle/android-libs.versions.toml"))
        }
        create("kotlinLibs") {
            from(files("gradle/kotlin-libs.versions.toml"))
        }
        create("thirdPartyLibs") {
            from(files("gradle/third-party-libs.versions.toml"))
        }
    }
}

rootProject.name = "Trash Architecture"
include(":app")
include(":bridge")
include(":core:data:network")
include(":core:design-system")
include(":core:push-notification")
include(":core:media")

include(":feature:sample:domain")
include(":feature:sample:data")
include(":feature:sample:presentation")
include(":feature:home:domain")
include(":feature:home:data")
include(":feature:home:presentation")
include(":feature:music:presentation")
