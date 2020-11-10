name := "Monads"

ThisBuild / scalaVersion := "2.12.8"

//ThisBuild / triggeredMessage := watched.clearWhentriggered

ThisBuild / autoStartServer := false

ThisBuild / scalacOptions ++= Seq(
  "-feature",
  "-deprecation",
  "-language:implicitConversions",
  "-language:higherKinds"
)

ThisBuild / shellPrompt := (_ => fancyPrompt(name.value))

def fancyPrompt(str: String): String =
  s"""
     |[info] Welcome to the ${cyan("Monads-Project")} project!
     |sbt> """.stripMargin

def cyan(projectName: String): String =
  scala.Console.CYAN + projectName + scala.Console.RESET

lazy val `fp-library` =
  project
    .in(file("./1-fp-library"))
    .settings(shellPrompt := (_ => fancyPrompt(name.value)))

lazy val `application-library` =
  project
    .in(file("./2-application-library"))
    .settings(shellPrompt := (_ => fancyPrompt(name.value)))
    .dependsOn(`fp-library`)

lazy val `end-of-the-world` =
  project
    .in(file("./3-end-of-the-world"))
    .settings(shellPrompt := (_ => fancyPrompt(name.value)))
    .dependsOn(`application-library`)



