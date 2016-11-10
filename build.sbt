name := "crible"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  filters,
  "com.typesafe.play" %% "play-cache" % "2.4.6",
  "org.mongodb.scala" %% "mongo-scala-driver" % "1.0.1",
  "com.wix" %% "accord-core" % "0.6",
  "org.mindrot" % "jbcrypt" % "0.3m",
  "com.typesafe.play" %% "play-mailer" % "5.0.0"
)

resolvers ++= Seq(
  "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases",
  "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
)

routesGenerator := InjectedRoutesGenerator

lazy val root = (project in file(".")).enablePlugins(PlayScala)
    