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

kotlin { jvmToolchain(libs.versions.jvmToolchain.get().toInt()) }

tasks {
  compileKotlin {
    compilerOptions.freeCompilerArgs.addAll(
        "-Xjvm-default=all",
        "-Xconsistent-data-class-copy-visibility",
    )
  }
}

spotless { kotlin { ktfmt() } }

val sourcesJar by
    tasks.registering(Jar::class) {
      archiveClassifier.set("sources")
      from(sourceSets.main.get().allSource)
    }

dokka {
  val version = libs.versions.paper.get().substringBefore("-")
  dokkaSourceSets.configureEach {
    externalDocumentationLinks {
      register("paper-docs") {
        url("https://jd.papermc.io/paper/$version/")
        packageListUrl("https://jd.papermc.io/paper/$version/element-list")
      }
      register("adventure-docs") {
        url("https://jd.advntr.dev/api/4.17.0/")
        packageListUrl("https://jd.advntr.dev/api/4.17.0/element-list")
      }
    }
  }
  dokkaPublications.html { includes.from(project.layout.projectDirectory.file("README.md")) }
}

val javadocJar by
    tasks.registering(Jar::class) {
      archiveClassifier.set("javadoc")
      from(tasks.dokkaGeneratePublicationHtml.flatMap { it.outputDirectory })
    }

publishing {
  repositories {
    maven {
      name = "md5lukasReposilite"

      url =
          uri(
              "https://repo.md5lukas.de/${if (version.toString().endsWith("-SNAPSHOT")) {
        "snapshots"
      } else {
        "releases"
      }}"
          )

      credentials(PasswordCredentials::class)
      authentication { create<BasicAuthentication>("basic") }
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
