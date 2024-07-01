plugins {
	java
	id("org.springframework.boot") version "3.3.0"
	id("io.spring.dependency-management") version "1.1.5"
}

group = "com.stoyanov.gateway"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion.set(JavaLanguageVersion.of(21))
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.kafka:spring-kafka")
	compileOnly("org.projectlombok:lombok")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	developmentOnly("org.springframework.boot:spring-boot-docker-compose")
	runtimeOnly("org.postgresql:postgresql")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.boot:spring-boot-testcontainers")
	testImplementation("org.springframework.kafka:spring-kafka-test")
	testImplementation("org.springframework.security:spring-security-test")
	testImplementation("org.testcontainers:junit-jupiter")
	testImplementation("org.testcontainers:kafka")
	testImplementation("org.testcontainers:postgresql")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

// Backend
tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.register<Exec>("runGatewayService") {
	dependsOn(tasks.bootRun)
}

tasks.named("build") {
	dependsOn(buildFlutterWeb) // copyFrontend
}

// Frontend
val buildFlutterWeb = tasks.register<Exec>("buildFlutterWeb") {
	workingDir = file("../frontend")
	commandLine("flutter", "build", "web")
}

//val copyFrontend = tasks.register<Exec>("copyFrontend") {
//	workingDir = file("../frontend/build")
//	commandLine("cp", "-r", "web", "../../gateway/src/main/resources/static")
//}
