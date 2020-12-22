/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Java application project to get you started.
 * For more details take a look at the 'Building Java & JVM projects' chapter in the Gradle
 * User Manual available at https://docs.gradle.org/6.7.1/userguide/building_java_projects.html
 */
val VERSION = "1.0.0"
val CI: Boolean = "true".equals(System.getenv("CI"))
val TOKEN: String = System.getenv("TOKEN") ?: "DRY"
val GITHUB_REF: String = System.getenv("GITHUB_REF") ?: "local"
val isMaster: Boolean = GITHUB_REF.equals("refs/heads/master")
val isLocal: Boolean = !CI
val snapshotVersion: String = "0." + String.format("%08x", GITHUB_REF.hashCode()) + "-SNAPSHOT"

val demoLibVersion = if (isMaster && !isLocal) "2.0.0" else snapshotVersion

group = "demo-app"
version = if (isMaster && !isLocal) VERSION else snapshotVersion

plugins {
    // Apply the application plugin to add support for building a CLI application in Java.
    application
    `maven-publish`
}

repositories {
    // Use JCenter for resolving dependencies.
    jcenter()
    mavenLocal()
    maven {
        name = "GitHubPackages"
        url = uri("https://maven.pkg.github.com/ModelingValueGroup/demo-lib")
        credentials {
            username = "" // can be anything but plugin requires it
            password = TOKEN
        }
    }
}

dependencies {
    // Use JUnit Jupiter API for testing.
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.2")

    // Use JUnit Jupiter Engine for testing.
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")

    // This dependency is used by the application.
    implementation("com.google.guava:guava:29.0-jre")
    implementation("demo-lib:lib:$demoLibVersion")
}

application {
    // Define the main class for the application.
    mainClass.set("demo.app.App")
}

tasks.test {
    // Use junit platform for unit tests.
    useJUnitPlatform()
}
