import org.jetbrains.compose.compose

fun properties(key: String) = project.findProperty(key).toString()

plugins {
    id("org.jetbrains.intellij") version "1.1.2"
    //id("org.jetbrains.grammarkit") version "2020.3"
    java
    kotlin("jvm") version "1.5.21"
    // __LATEST_COMPOSE_RELEASE_VERSION__
    id("org.jetbrains.compose") version "1.0.0-alpha2"
    id("idea")
}

group = "cn.beingyi"
version = "0.0.1"

// Configure project's dependencies
repositories {
    mavenCentral()
    google()
    maven { url = uri("https://maven.pkg.jetbrains.space/public/p/compose/dev") }
}
dependencies {
    //implementation("org.jetbrains.compose.material:material:")
    implementation(compose.desktop.currentOs)

    implementation("org.dom4j:dom4j:2.1.1")
    implementation("commons-io:commons-io:2.11.0")
    implementation("org.apache.commons:commons-lang3:3.12.0")
    implementation("com.alibaba:fastjson:1.2.76")
    implementation("com.squareup:kotlinpoet:1.9.0")


    val IntellijCompilePath="F:\\Application\\IntelliJ IDEA 2020.3"
    compileOnly(fileTree(mapOf("dir" to "$IntellijCompilePath/plugins/java/lib", "include" to listOf("*.jar"))))
    compileOnly(fileTree(mapOf("dir" to "$IntellijCompilePath/lib", "include" to listOf("*.jar"))))

    testCompileOnly(fileTree(mapOf("dir" to "$IntellijCompilePath/plugins/java/lib", "include" to listOf("*.jar"))))
    testCompileOnly(fileTree(mapOf("dir" to "$IntellijCompilePath/lib", "include" to listOf("*.jar"))))

    testCompileOnly(group= "junit", name= "junit", version= "4.12")

}

intellij {
    version.set("2021.2.1")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}

