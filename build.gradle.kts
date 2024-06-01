import org.jetbrains.dokka.gradle.DokkaTask

plugins {
  with(libs.plugins) {
    alias(kotlin)
    alias(dokka)
    alias(spotless)
  }
  `maven-publish`
}

repositories {
  mavenCentral()

  maven("https://repo.papermc.io/repository/maven-public/")
  maven("https://repo.md5lukas.de/public/")
}

dependencies {
  implementation(libs.paper)
  implementation(libs.stdlib)
}

kotlin {
  jvmToolchain(libs.versions.jvmToolchain.get().toInt())
}

tasks {
  compileKotlin {
    compilerOptions.freeCompilerArgs.addAll(
        "-Xjvm-default=all",
    )
  }
}

spotless { kotlin { ktfmt() } }

val sourcesJar by tasks.creating(Jar::class) {
  archiveClassifier.set("sources")
  from(sourceSets.main.get().allSource)
}

val dokkaHtml by tasks.getting(DokkaTask::class) {
  dokkaSourceSets {
    configureEach {
      includes.from("README.md")
      val version = libs.versions.paper.get().substringBefore("-")
      externalDocumentationLink(
        "https://jd.papermc.io/paper/$version/",
        "https://jd.papermc.io/paper/$version/element-list")
      externalDocumentationLink(
        "https://jd.advntr.dev/api/4.17.0/",
        "https://jd.advntr.dev/api/4.17.0/element-list")
    }
  }
}

val javadocJar by tasks.creating(Jar::class) {
  dependsOn(tasks.dokkaHtml)
  archiveClassifier.set("javadoc")
  from(tasks.dokkaHtml)
}

publishing {
  repositories {
    maven {
      name = "md5lukasReposilite"

      url = uri("https://repo.md5lukas.de/${if (version.toString().endsWith("-SNAPSHOT")) {
        "snapshots"
      } else {
        "releases"
      }}")

      credentials(PasswordCredentials::class)
      authentication {
        create<BasicAuthentication>("basic")
      }
    }
  }
  publications {
    create<MavenPublication>("maven") {
      from(components["kotlin"])
      artifact(sourcesJar)
      artifact(javadocJar)
    }
  }
}
