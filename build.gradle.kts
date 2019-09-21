group = "de.richargh"
version = "0.1-SNAPSHOT"

val tornadofx_version: String by project
val jackson_version: String by project
val junit5_version: String by project
val assertj_version: String by project

plugins {
    kotlin("jvm") version "1.3.50"
    application
}

repositories {
    mavenCentral()
    jcenter()
}

tasks.compileKotlin {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClassName = "de.richargh.buggetfx.MyAppKt"
}

dependencies {
    implementation(kotlin("stdlib"))

    implementation("no.tornado:tornadofx:$tornadofx_version")
    implementation("com.fasterxml.jackson.core:jackson-databind:$jackson_version")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jackson_version")

    testImplementation("org.junit.jupiter:junit-jupiter-api:$junit5_version")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:$junit5_version")
    testImplementation("org.junit.jupiter:junit-jupiter-params:$junit5_version")
    testImplementation("org.assertj:assertj-core:$assertj_version")
}

// more settings online: https://docs.gradle.org/current/userguide/java_testing.html
tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

tasks.wrapper {
    gradleVersion = "5.6.1"
}
