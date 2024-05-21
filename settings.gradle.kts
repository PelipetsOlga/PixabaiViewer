pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
//        maven(url = "https://packagecloud.io/cux/public-compose-gradle/maven2")
        maven(url = "https://packagecloud.io/cux/public-compose-dev-gradle/maven2")
    }
}

rootProject.name = "PixApp"
include(":app")
include(":data")
include(":domain")
include(":base")
include(":home_ui")
include(":photo_ui")
