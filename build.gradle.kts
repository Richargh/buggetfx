group = "de.richargh"
version = "0.1-SNAPSHOT"

val junit5_version: String by project

plugins {
    kotlin("jvm") version "1.2.31"
    application
}


repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.5.1")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.5.1")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.5.1")
}

tasks.wrapper {
    gradleVersion = "5.6.1"
}
