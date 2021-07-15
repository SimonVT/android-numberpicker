import com.intermedia.numberpicker.Config
import java.util.Properties

plugins {
    id("com.android.library")
    id("maven-publish")
}

android {
    compileSdkVersion(Config.Android.compileSdkVersion)
    buildToolsVersion(Config.Android.buildToolsVersion)

    defaultConfig {
        minSdkVersion(Config.Android.minSdkVersion)
        targetSdkVersion(Config.Android.targetSdkVersion)
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

val localProperties = Properties()
val localPropertiesFile = project.rootProject.file("local.properties")
if (localPropertiesFile.exists()) {
    localProperties.load(localPropertiesFile.inputStream())
}
val publishUrl: String = localProperties.getProperty("nexusUrl")
val publishUser: String = localProperties.getProperty("nexusUsername")
val publishPass: String = localProperties.getProperty("nexusPassword")

afterEvaluate {
    publishing {
        publications {
            register("numberpicker", MavenPublication::class) {
                groupId = Config.group
                artifactId = Config.artifact
                version = Config.version
                from(components["release"])

                pom {
                    name.set(Config.name)
                    description.set(Config.description)
                    url.set(Config.url)

                    licenses {
                        license {
                            name.set(Config.licenseName)
                            url.set(Config.licenseUrl)
                        }
                    }
                    organization {
                        name.set(Config.organizationName)
                    }
                    scm {
                        connection.set(Config.url)
                        developerConnection.set(Config.url)
                        url.set(Config.url)
                    }
                }
            }
        }
        repositories {
            maven(publishUrl) {
                credentials {
                    username = publishUser
                    password = publishPass
                }
            }
        }
    }
}