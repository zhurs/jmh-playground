plugins {
    kotlin("jvm") version "2.0.0"
    id("me.champeau.jmh") version "0.7.2"
}

group = "com.jetbrains.tbe"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}

jmh {
    includes = listOf(
        """RandomIterations"""
    )
    benchmarkMode = listOf("avgt")
//    profilers = listOf("perfasm")
    timeUnit = "ns"

    warmupIterations = 2
    iterations = 3
    fork = 2
    threads = 10

    warmup = "2s"
    timeOnIteration = "2s"
}