name := "crible"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-cache" % "2.4.6",
  "org.scaldi" %% "scaldi-play" % "0.5.13",
  "com.websudos" %% "phantom-dsl" % "1.22.0"
)

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

resolvers ++= Seq(
  "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases",
  "Websudos" at "https://dl.bintray.com/websudos/oss-releases/"
)

routesGenerator := InjectedRoutesGenerator

lazy val root = (project in file(".")).enablePlugins(PlayScala)
    