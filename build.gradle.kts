plugins {
    kotlin("jvm") version "1.3.40"
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
    val ktorVersion = "1.2.2"
    val exposedVersion = "0.15.1"
    val hikariVersion = "3.3.1"
    val mariadbClientVersion = "2.4.2"
    val logbackVersion = "1.2.3"

    implementation(kotlin("stdlib-jdk8"))

    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    implementation("org.jetbrains.exposed:exposed:$exposedVersion")
    implementation("com.zaxxer:HikariCP:$hikariVersion")
    implementation("org.mariadb.jdbc:mariadb-java-client:$mariadbClientVersion")

    "io.ktor:ktor".let {
        implementation("$it-server-netty:$ktorVersion")
        implementation("$it-server-core:$ktorVersion")
        implementation("$it-gson:$ktorVersion")

        testImplementation("$it-server-tests:$ktorVersion")
    }
}
