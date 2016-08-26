name := "crible"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  filters,
  "com.typesafe.play" %% "play-cache" % "2.4.6",
  "com.websudos" %% "phantom-dsl" % "1.27.0",
  "com.websudos" %%  "phantom-reactivestreams" % "1.27.0",
  "com.wix" %% "accord-core" % "0.6-SNAPSHOT",
  "org.mindrot" % "jbcrypt" % "0.3m"
)

resolvers ++= Seq(
  "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases",
  "Websudos" at "https://dl.bintray.com/websudos/oss-releases/",
  "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
)

routesGenerator := InjectedRoutesGenerator

lazy val root = (project in file(".")).enablePlugins(PlayScala)
    