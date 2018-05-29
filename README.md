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

## Contributing

By participating in this project, you agree to abide by the thoughtbot [code of conduct].

[code of conduct]: https://thoughtbot.com/open-source-code-of-conduct

Fork, then clone the repo:

    git clone git@github.com/wellcometrust/sierra-streams-source.git

Make your change. Test your change!

Push to your fork and [submit a pull request][pr].

[pr]: https://github.com/wellcometrust/sierra-streams-source/compare/

At this point you're waiting on us. We like to at least comment on pull requests
within three business days. We may suggest some changes or improvements or alternatives.

Some things that will increase the chance that your pull request is accepted:

* Ensure you add tests covering your change
* Write a [good commit message][commit].

[commit]: http://tbaggery.com/2008/04/19/a-note-about-git-commit-messages.html

## Development

If you want to release this library you'll need credentials to authenticate with Travis and Sonatype.

### Releasing to Sonatype

Create a file `credentials.sbt` in the root of the repo with the following contents (but with the correct details).

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