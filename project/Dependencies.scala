import sbt._

object Dependencies {
  object Play {
    val version = "2.6.6"
    val lib = "com.typesafe.play" %% "play" % version
    val filters = play.sbt.PlayImport.filters
    val ws = play.sbt.PlayImport.ws
    val json = "com.typesafe.play" %% "play-json" % "2.6.6"
    val test = "com.typesafe.play" %% "play-test" % version
    val guice = play.sbt.PlayImport.guice

    val mailer = "com.typesafe.play" %% "play-mailer" % "6.0.1"
    val mailerGuice = "com.typesafe.play" %% "play-mailer-guice" % "6.0.1"
  }

  object Akka {
    private[this] val version = "2.5.6"
    val actor = "com.typesafe.akka" %% "akka-actor" % version
    val cluster = "com.typesafe.akka" %% "akka-cluster" % version
    val contrib = "com.typesafe.akka" %% "akka-contrib" % version
    val persistence = "com.typesafe.akka" %% "akka-persistence-experimental" % version
    val remoting = "com.typesafe.akka" %% "akka-remote" % version
    val log4j = "com.typesafe.akka" %% "akka-slf4j" % version
    val testkit = "com.typesafe.akka" %% "akka-testkit" % version
  }

  object Database {
    val hikariCp = "com.zaxxer" % "HikariCP" % "2.7.2"
    val postgres = "org.postgresql" % "postgresql" % "9.4.1212"
  }

  object GraphQL {
    val sangria = "org.sangria-graphql" %% "sangria" % "1.3.0"
    val playJson = "org.sangria-graphql" %% "sangria-play-json" % "1.0.4"
    val circe = "org.sangria-graphql" %% "sangria-circe" % "1.1.0"
  }

  object Spark {
    val core = "org.apache.spark" %% "spark-core" % "1.4.1" exclude("org.slf4j", "slf4j-log4j12")
  }

  object WebJars {
    val requireJs = "org.webjars" % "requirejs" % "2.3.3"
    val bootstrap = "org.webjars" % "bootstrap" % "3.3.7"
    val underscore = "org.webjars" % "underscorejs" % "1.8.3"
    val d3 = "org.webjars" % "d3js" % "3.5.17"
    val nvd3 = "org.webjars" % "nvd3-community" % "1.7.0"
    val jquery = "org.webjars" % "jquery" % "3.2.1"
    val materialize = "org.webjars" % "materializecss" % "0.99.0"
  }

  object Serialization {
    val circeVersion = "0.8.0"
  }

  object Metrics {
    private[this] val version = "3.2.5"
    val jvm = "io.dropwizard.metrics" % "metrics-jvm" % version withSources()
    val ehcache = "io.dropwizard.metrics" % "metrics-ehcache" % version withSources() intransitive()
    val healthChecks = "io.dropwizard.metrics" % "metrics-healthchecks" % version withSources() intransitive()
    val json = "io.dropwizard.metrics" % "metrics-json" % version withSources()
    val servlets = "io.dropwizard.metrics" % "metrics-servlets" % version withSources() intransitive()
    val graphite = "io.dropwizard.metrics" % "metrics-graphite" % version withSources() intransitive()

    val metrics = "nl.grons" %% "metrics-scala" % "3.5.9" withSources()
    val jettyServlet = "org.eclipse.jetty" % "jetty-servlet" % "9.4.6.v20170531" withSources()
  }

  object ScalaJS {
    val jQueryVersion = "0.9.2"
    val scalaTagsVersion = "0.6.7"
    val domVersion = "0.9.3"
    val definitelyScalaVersion = "1.0.2"
  }

  object Utils {
    val scapegoatVersion = "1.3.3"
    val enumeratumVersion = "1.5.14"

    val betterFiles = "com.github.pathikrit" %% "better-files" % "3.1.0"
  }

  object Testing {
    val gatlingVersion = "2.3.0"
    val gatlingCore = "io.gatling" % "gatling-test-framework" % gatlingVersion % "test"
    val gatlingCharts = "io.gatling.highcharts" % "gatling-charts-highcharts" % gatlingVersion % "test"
  }
}
