plugins {
    kotlin("jvm") version "1.3.71"
}

group = "com.antosha4e"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    implementation("org.apache.logging.log4j:log4j-api:2.13.1")
    implementation("org.apache.logging.log4j:log4j-slf4j-impl:2.13.1")
    implementation("org.apache.logging.log4j:log4j-core:2.13.1")

    testImplementation("junit:junit:4.12")
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }

    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}
