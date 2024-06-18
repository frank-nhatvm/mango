import com.vanniktech.maven.publish.JavaLibrary
import com.vanniktech.maven.publish.JavadocJar
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.8.21"
    id("com.vanniktech.maven.publish") version "0.27.0"
}

group = "com.fatherofapps"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("commons-codec:commons-codec:1.17.0")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}



publishing {
    repositories {
        val repositoryDir = rootProject.file("public").resolve("fpublic")
            .resolve("repository")
        maven {
            name = "MangoReleases"
            url = repositoryDir.resolve("releases").toURI()
        }
    }

    publications {

        mavenPublishing{
            configure(
                JavaLibrary(
                // configures the -javadoc artifact, possible values:
                // - `JavadocJar.None()` don't publish this artifact
                // - `JavadocJar.Empty()` publish an emprt jar
                // - `JavadocJar.Javadoc()` to publish standard javadocs
                javadocJar = JavadocJar.Javadoc(),
                // whether to publish a sources jar
                sourcesJar = true,
            )
            )
        }

        create<MavenPublication>("mango") {
            groupId = "com.fatherofapps"
            artifactId = "mango"
            version = "1.0"

            pom {
                name = "mango"
                description =
                    "A library to generate route for Jetpack Compose Navigation by using KSP"
                url = "https://github.com/frank-nhatvm/mango"
                licenses {
                    license {
                        name = "The Apache License, Version 2.0"
                        url = "http://www.apache.org/licenses/LICENSE-2.0.txt"
                    }
                }
                developers {
                    developer {
                        id = "frank_vu"
                        name = "Frank Vu"
                        email = "nhat.thtb@gmail.com"
                    }
                }
                scm {
                    connection = "scm:git@github.com:frank-nhatvm/mango.git"
                    developerConnection = "scm:git@github.com:frank-nhatvm/mango.git"
                    url = "https://github.com/frank-nhatvm/mango"
                }
            }
        }
    }
}
