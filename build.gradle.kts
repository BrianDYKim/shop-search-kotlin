import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val coroutineVersion = "1.6.3"
val mockkVersion = "1.10.3"
val kotestVersion = "5.3.2"

plugins {
    id("org.springframework.boot") version "2.7.3"
    id("io.spring.dependency-management") version "1.0.15.RELEASE"
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
}

group = "team.bakkas"
version = "0.0.1"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

dependencies {
    // Kotlin Standard Library
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // Jackson
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("com.fasterxml.jackson.module:jackson-module-afterburner")

    // Kotlin Coroutines
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutineVersion")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:$coroutineVersion")

    // Elasticsearch
    implementation("org.springframework.boot:spring-boot-starter-data-elasticsearch")

    // Test Implementation
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    // mockk
    testImplementation("io.mockk:mockk:$mockkVersion")
    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion") // for kotest framework
    testImplementation("io.kotest:kotest-assertions-core:$kotestVersion") // for kotest core jvm assertions
    testImplementation("io.kotest:kotest-property:$kotestVersion") // for kotest property test
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutineVersion")

    // Annotation Processing Tool
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    // 외부 라이브러리 import
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    // 해당 dependency가 있어야 coroutine을 이용해서 grpc를 이용 가능하다
    api("io.grpc:grpc-kotlin-stub:1.3.0")

    // Armeria
    implementation("com.linecorp.armeria:armeria-grpc")
    implementation("com.linecorp.armeria:armeria-spring-boot2-webflux-starter")
}

dependencyManagement {
    imports {
        mavenBom("com.linecorp.armeria:armeria-bom:0.99.9")
        mavenBom("io.netty:netty-bom:4.1.51.Final")
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
