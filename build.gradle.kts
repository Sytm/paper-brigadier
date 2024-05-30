plugins {
  with(libs.plugins) {
    alias(kotlin)
    alias(spotless)
  }
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

tasks {
  compileKotlin {
    compilerOptions.freeCompilerArgs.addAll(
        "-Xjvm-default=all",
        "-Xlambdas=indy",
    )
  }
}

spotless { kotlin { ktfmt() } }