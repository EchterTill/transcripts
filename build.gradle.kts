plugins {
    kotlin("jvm") version "1.9.23"
}

group = "de.till"
version = "0.0.1"

val jdaVersion: String by project
val junitVersion: String by project


repositories {
    mavenCentral()
    maven("https://nexus.flawcra.cc/repository/mirrors/")
    maven("https://repo.fruxz.dev/releases/")
    maven("https://m2.coly.dev/releases")

}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    implementation("org.jetbrains:markdown:0.6.1")
    implementation("dev.fruxz:ascend:2024.1.1")
    implementation("net.dv8tion:JDA:$jdaVersion")
    implementation("dev.coly:JDATesting:0.6.0")
    implementation("org.junit.jupiter:junit-jupiter:$junitVersion")
    implementation("com.openhtmltopdf:openhtmltopdf-core:1.0.10")
    implementation("com.openhtmltopdf:openhtmltopdf-pdfbox:1.0.10")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}