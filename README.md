# sierra-streams-source

A library for providing Akka Streams from objects in a Sierra API.

[![Build Status](https://travis-ci.org/wellcometrust/sierra-streams-source.svg?branch=master)](https://travis-ci.org/wellcometrust/sierra-streams-source)

## Installation

```scala
libraryDependencies ++= Seq(
  "uk.ac.wellcome" %% "sierra-streams-source" % "0.1"
)
```

sierra-streams-source is published for Scala 2.11 and Scala 2.12

## Basic Usage

```Scala
import akka.stream.scaladsl.Sink
import uk.ac.wellcome.sierra.{SierraSource, ThrottleRate}
import scala.concurrent.duration._

val sierraSource = SierraSource(
  sierraUrl, 
  oauthKey, 
  oauthSecret, 
  ThrottleRate(4, 1.second)
)(
  "items", 
  Map(
    "updatedDate" -> "[2013-12-10T17:16:35Z,2013-12-13T21:34:35Z]"
  )
)

val eventualJsonList = sierraSource.runWith(Sink.seq[Json])

eventualJsonList.map(jsonList => {
  // Do stuff
})
```

## Development

If you want to work on our release this library.

### Releasing to Sonatype

Create a file `credentials.sbt` in the root of the repo with the following contents (but with the correct details)..

```sbt
credentials += Credentials("Sonatype Nexus Repository Manager",
       "oss.sonatype.org",
       "(Sonatype user name)",
       "(Sonatype password)")

pgpPassphrase := Some("(PGP password)".toCharArray)
```

Then run `publishSigned` and `sonatypeRelease` in sbt to push a release:

```sh
sbt ++2.11.11 publishSigned sonatypeRelease;
```

### Packaging for Travis

To encrypt `credentials.sbt` for Travis you can use the following command:

```sh
docker run -v $(pwd):/project \
  -v  ~/.travis:/root/.travis \
  -it skandyla/travis-cli \
  encrypt-file credentials.sbt
```

You will then need to copy the specified output to `.travis.yml` if different from what is already in that file.

For example:

```yml
  - stage: release
     scala: 2.12.6
     script:
     # Output like this will be specified from the travis cli tool
     - openssl aes-256-cbc -K $encrypted_83630750896a_key -iv $encrypted_83630750896a_iv -in credentials.sbt.enc -out credentials.sbt -d
```