ThisBuild / version      := "0.0.0-SNAPSHOT"
ThisBuild / organization := "app.wishingtree"
ThisBuild / scalaVersion := "3.3.0"

lazy val root = (project in file("."))
  .settings(
    name := "zio-jedis",
    libraryDependencies ++= Seq(
      "com.alterationx10" %% "opinionated-zio"      % "0.0.4",
      "com.alterationx10" %% "opinionated-zio-test" % "0.0.4" % Test,
      "redis.clients"      % "jedis"                % "4.4.3",
    ),
  )
