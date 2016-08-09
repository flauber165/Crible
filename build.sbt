name := "crible"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-cache" % "2.4.6",
  "com.websudos" %% "phantom-dsl" % "1.22.0",
  "com.websudos" %%  "phantom-reactivestreams" % "1.22.0"
)

resolvers ++= Seq(
  "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases",
  "Websudos" at "https://dl.bintray.com/websudos/oss-releases/"
)

routesGenerator := InjectedRoutesGenerator

lazy val root = (project in file(".")).enablePlugins(PlayScala)
    