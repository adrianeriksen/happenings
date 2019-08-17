plugins {
    kotlin("jvm") version "1.3.41"
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
    val ktorVersion = "1.2.3"
    val argonVersion = "2.5"
    val exposedVersion = "0.16.4"
    val hikariVersion = "3.3.1"
    val mariadbClientVersion = "2.4.2"
    val logbackVersion = "1.2.3"
    val junitVersion = "5.5.1"

    implementation(kotlin("stdlib-jdk8"))

    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    implementation("org.jetbrains.exposed:exposed:$exposedVersion")
    implementation("com.zaxxer:HikariCP:$hikariVersion")
    implementation("org.mariadb.jdbc:mariadb-java-client:$mariadbClientVersion")
    implementation("de.mkammerer:argon2-jvm:$argonVersion")

    "io.ktor:ktor".let {
        implementation("$it-server-netty:$ktorVersion")
        implementation("$it-server-core:$ktorVersion")
        implementation("$it-gson:$ktorVersion")
        implementation("$it-auth-jwt:$ktorVersion")

        testImplementation("$it-server-tests:$ktorVersion")
        testImplementation("$it-server-test-host:$ktorVersion")
    }

    "org.junit.jupiter:junit-jupiter".let {
        testImplementation("$it-api:$junitVersion")
        testRuntime("$it-engine:$junitVersion")
    }
}

tasks {
    "test"(Test::class) {
        useJUnitPlatform()
    }
}
