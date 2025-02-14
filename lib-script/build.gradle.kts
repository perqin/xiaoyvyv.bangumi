import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("java-library")
    alias(libs.plugins.jetbrainsKotlinJvm)
}


kotlin {
    compilerOptions.jvmTarget.set(JvmTarget.JVM_1_8)
}


java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    api(libs.jsoup)
    api(libs.sqlite.jdbc)
    api(libs.log4j.core)
    api(libs.slf4j.simple)

    // api("com.huaban:jieba-analysis:1.0.2")
    api("com.google.code.gson:gson:2.10.1")
}

/**
 * Command Gen ios resource
 *
 * "/Applications/Android Studio.app/Contents/jre/Contents/Home/bin/java" -jar language.jar
 *
 * "/Users/why/Library/Android/sdk/platform-tools/adb" connect 192.168.xxx
 *
 * java -jar language.jar -name LiveIn
 * java -jar language.jar -name LiveStatus
 * java -jar language.jar -name GAS!
 */
tasks.jar {
    archiveFileName.set("bgm-script.jar")
    duplicatesStrategy = DuplicatesStrategy.INCLUDE

    manifest {
        attributes["Main-Class"] = "com.xiaoyv.script.Main"
        attributes["Charset"] = "UTF-8"
    }

    from(configurations.runtimeClasspath.get().map(::zipTree))

    isZip64 = true
    entryCompression = ZipEntryCompression.DEFLATED
}