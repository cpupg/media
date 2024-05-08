buildscript {
    repositories {
        maven { setUrl("https://maven.aliyun.com/repository/public") }
        maven { setUrl("https://maven.aliyun.com/repository/gradle-plugin") }
    }
}

rootProject.name = "media"

include("application")
include("cli")
include("common")
include("config")
include("dataaccess")
include("service")
include("web")
