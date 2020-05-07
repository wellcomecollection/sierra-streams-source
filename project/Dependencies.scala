import sbt._

object Dependencies {
  lazy val versions = new {
    val akka = "2.6.4"
    val circe = "0.13.0"
    val scalatest = "3.1.1"
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
