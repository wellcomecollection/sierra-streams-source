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
    "com.typesafe.akka" %% "akka-stream" % versions.akka,
    "com.typesafe.akka" %% "akka-stream-testkit" % versions.akka % Test
  )

  val circeDependencies = Seq(
    "io.circe" %% "circe-core" % versions.circe,
    "io.circe" %% "circe-parser" % versions.circe,
    "io.circe" %% "circe-optics" % versions.circe

  )

  val testDependencies = Seq(
    "com.github.tomakehurst" % "wiremock" % "2.11.0" % Test,
    "org.scalatest" %% "scalatest" % versions.scalatest % Test
  )

  val libraryDependencies = Seq(
    "org.scalaj" %% "scalaj-http" % "2.3.0"
  ) ++ akkaDependencies ++ circeDependencies ++ testDependencies ++ logbackDependencies
}
