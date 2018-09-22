name := "akka-quickstart-scala"

version := "1.0"

scalaVersion := "2.11.5"

lazy val akkaVersion = "2.5.6"

mainClass in (Compile,run) := Some("com.lightbend.akka.sample.MainApp")

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-testkit" % akkaVersion,
  "org.scalatest" %% "scalatest" % "3.0.1" % "test"
)

