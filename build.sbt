import sbt.Resolver
import sbtrelease.Version.Bump

organization := "uk.ac.wellcome"

name := "sierra-streams-source"

crossScalaVersions := Seq("2.11.11", "2.12.6")


val versions = new {
  val logback = "1.1.8"
  val scalatest = "3.0.1"
  val circeVersion = "0.8.0"
}

val circeDependencies = Seq(
  "io.circe" %% "circe-core" % versions.circeVersion,
  "io.circe" %% "circe-generic"% versions.circeVersion,
  "io.circe" %% "circe-parser"% versions.circeVersion,
  "io.circe" %% "circe-optics" % versions.circeVersion
)

libraryDependencies := Seq(
  "com.github.tomakehurst" % "wiremock" % "2.11.0" % Test,
  "org.scalaj" %% "scalaj-http" % "2.3.0",
  "org.scalatest" %% "scalatest" % versions.scalatest % Test,
  "ch.qos.logback" % "logback-classic" % versions.logback,
  "org.slf4j" % "slf4j-api" % "1.7.25",
  "com.typesafe.akka" %% "akka-stream" % "2.5.6",
  "com.typesafe.akka" %% "akka-stream-testkit" % "2.5.6" % Test
) ++ circeDependencies

resolvers += Resolver.sonatypeRepo("releases")

scalacOptions ++= Seq(
  "-deprecation",
  "-unchecked",
  "-encoding",
  "UTF-8",
  "-Xlint",
  "-Xverify",
  "-feature",
  "-language:postfixOps"
)

dynverSonatypeSnapshots in ThisBuild := true

publishTo := Some(
  if (isSnapshot.value)
    Opts.resolver.sonatypeSnapshots
  else
    Opts.resolver.sonatypeStaging
)

useGpg := false

parallelExecution in Test := false

pgpPublicRing := baseDirectory.value / "pgp-key" / "pubring.asc"
pgpSecretRing := baseDirectory.value / "pgp-key" / "secring.asc"

import sbtrelease._
// we hide the existing definition for setReleaseVersion to replace it with our own
import sbtrelease.ReleaseStateTransformations.{setReleaseVersion=>_,_}
def setVersionOnly(selectVersion: Versions => String): ReleaseStep =  { st: State =>
  val vs = st.get(ReleaseKeys.versions).getOrElse(sys.error("No versions are set! Was this release part executed before inquireVersions?"))
  val selected = selectVersion(vs)

  st.log.info("Setting version to '%s'." format selected)
  val useGlobal =Project.extract(st).get(releaseUseGlobalVersion)
  val versionStr = (if (useGlobal) globalVersionString else versionString) format selected

  reapply(Seq(
    if (useGlobal) version in ThisBuild := selected
    else version := selected
  ), st)
}

lazy val setReleaseVersion: ReleaseStep = setVersionOnly(_._1)

releaseVersion := { ver: String =>Version(ver)
    .map(_.withoutQualifier)
    .map(_.bump(Bump.Next).string).getOrElse(versionFormatError)
}

releaseProcess := Seq(
  checkSnapshotDependencies,
  inquireVersions,
  setReleaseVersion,
  tagRelease,
  publishArtifacts,
  pushChanges
)
