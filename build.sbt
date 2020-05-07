import java.util.UUID

val projectName = "sierra-streams-source"
val projectVersion = "1.0.1"

val settings: Seq[Def.Setting[_]] = Seq(
  scalaVersion := "2.12.6",
  organization := "uk.ac.wellcome",
  scalacOptions ++= Seq(
    "-deprecation",
    "-unchecked",
    "-encoding",
    "UTF-8",
    "-Xlint",
    "-Xverify",
    "-Xfatal-warnings",
    "-feature",
    "-language:postfixOps"
  ),
  parallelExecution in Test := false,
  resolvers ++= Seq(
    "S3 releases" at "s3://releases.mvn-repo.wellcomecollection.org/"
  ),
  publishMavenStyle := true,
  publishTo := Some(
    "S3 releases" at "s3://releases.mvn-repo.wellcomecollection.org/"
  ),
  publishArtifact in Test := true,
  version := projectVersion
)

lazy val root = (project in file("."))
    .withId(projectName)
    .settings(settings)
    .settings(libraryDependencies ++= Dependencies.libraryDependencies)