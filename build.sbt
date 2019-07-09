lazy val akkaHttpVersion = "10.1.8"
lazy val akkaVersion    = "2.6.0-M4"

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization    := "com.example",
      scalaVersion    := "2.12.8"
    )),
    name := "chotaUrl",
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-http"            % akkaHttpVersion,
      "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
      "com.typesafe.akka" %% "akka-http-xml"        % akkaHttpVersion,
      "com.typesafe.akka" %% "akka-stream"          % akkaVersion,
      "com.softwaremill.macwire" %% "macros" % "2.3.1" % "provided",
      "com.softwaremill.macwire" %% "macrosakka" % "2.3.1" % "provided",
      "com.softwaremill.macwire" %% "util" % "2.3.1",
      "com.softwaremill.macwire" %% "proxy" % "2.3.1",
      "org.mongodb.scala" %% "mongo-scala-driver" % "2.4.2",
      "commons-codec" % "commons-codec" % "1.9",
      "net.debasishg" %% "redisclient" % "3.10",
      "org.apache.commons" % "commons-lang3" % "3.0",

      "com.typesafe.akka" %% "akka-http-testkit"    % akkaHttpVersion % Test,
      "com.typesafe.akka" %% "akka-testkit"         % akkaVersion     % Test,
      "com.typesafe.akka" %% "akka-stream-testkit"  % akkaVersion     % Test,
      "org.scalatest"     %% "scalatest"            % "3.0.5"         % Test
    )
  )
