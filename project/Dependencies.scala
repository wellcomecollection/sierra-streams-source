import sbt._

object Dependencies {
  lazy val versions = new {
    val aws = "1.11.225"
    val akka = "2.5.9"
    val akkaStreamAlpakka = "0.20"
    val circe = "0.9.0"
    val circeYaml = "0.8.0"
    val scalatest = "3.0.1"
    val logback = "1.2.3"
  }

  val logbackDependencies = Seq(
    "ch.qos.logback" % "logback-classic" % versions.logback
  )

  val akkaDependencies: Seq[ModuleID] = Seq(
    "com.typesafe.akka" %% "akka-actor" % versions.akka,
    "com.typesafe.akka" %% "akka-stream" % versions.akka
  )

  val circeDependencies = Seq(
    "io.circe" %% "circe-core" % versions.circe,
    "io.circe" %% "circe-parser" % versions.circe
  )

  val testDependencies = Seq(
    "org.scalatest" %% "scalatest" % versions.scalatest % Test
  )

  val libraryDependencies = Seq(
    "com.github.tomakehurst" % "wiremock" % "2.11.0" % Test,
    "org.scalaj" %% "scalaj-http" % "2.3.0",
    "org.scalatest" %% "scalatest" % versions.scalatest % Test,
    "ch.qos.logback" % "logback-classic" % versions.logback,
    "org.slf4j" % "slf4j-api" % "1.7.25",
    "com.typesafe.akka" %% "akka-stream" % "2.5.6",
    "com.typesafe.akka" %% "akka-stream-testkit" % "2.5.6" % Test
  ) ++ circeDependencies
}
