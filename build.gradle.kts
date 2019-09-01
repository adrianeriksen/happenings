plugins {
    kotlin("jvm") version "1.3.50"
    id("de.fayard.buildSrcVersions") version "0.4.2"
    application
}

repositories {
    jcenter()
    maven("https://kotlin.bintray.com/ktor")
}

application {
    group = "io.sixtysix.happenings"
    version = "0.0.1-SNAPSHOT"
    mainClassName = "io.ktor.server.netty.EngineMain"
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    implementation(Libs.logback_classic)
    implementation(Libs.exposed)
    implementation(Libs.hikaricp)
    implementation(Libs.mariadb_java_client)
    implementation(Libs.argon2_jvm)

    implementation(Libs.ktor_server_core)
    implementation(Libs.ktor_server_netty)
    implementation(Libs.ktor_gson)
    implementation(Libs.ktor_auth_jwt)

    testImplementation(Libs.ktor_server_tests)
    testImplementation(Libs.ktor_server_test_host)

    testRuntime(Libs.junit_jupiter_engine)
    testImplementation(Libs.junit_jupiter_api)
}

tasks {
    "test"(Test::class) {
        useJUnitPlatform()
    }
}
