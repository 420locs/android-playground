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
        create("thirdPartyLibs") {
            from(files("gradle/thirdPartyLibs.versions.toml"))
        }
        create("dataSourceLibs") {
            from(files("gradle/dataSourceLibs.versions.toml"))
        }
    }
}

rootProject.name = "Trash Architecture"
include(":app")
include(":bridge")
include(":core:data:network")

include(":feature:sample:data")
include(":feature:sample:domain")
include(":feature:sample:presentation")
