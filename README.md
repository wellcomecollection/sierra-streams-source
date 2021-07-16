# sierra-streams-source

This repository used to provide an [Akka Source](https://doc.akka.io/docs/akka-http/current/routing-dsl/source-streaming-support.html) for objects from the [Sierra APIs](https://developer.iii.com/docs).
The code has been split into a couple of places:

-	The OAuth client credentials flow is now handled by [SierraOauthHttpClient in the scala-libs repo](https://github.com/wellcomecollection/scala-libs/blob/bac3eb3c165018bafa5f3ca3631488e26bf1320b/http/src/main/scala/weco/http/client/sierra/SierraOauthHttpClient.scala)
-	The SierraPageSource and SierraSource classes have moved into [the pipeline repo](https://github.com/wellcomecollection/catalogue-pipeline/tree/d17085091c130d2ea0256ecc20de19502632005b/sierra_adapter/sierra_reader/src/main/scala/weco/pipeline/sierra_reader/source)
