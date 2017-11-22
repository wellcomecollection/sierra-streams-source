# sierra-streams-source

A library for providing Akka Streams from objects in a Sierra API 

## Basic Usage

```Scala
import akka.stream.scaladsl.Sink
import uk.ac.wellcome.sierra.{SierraSource, ThrottleRate}

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
