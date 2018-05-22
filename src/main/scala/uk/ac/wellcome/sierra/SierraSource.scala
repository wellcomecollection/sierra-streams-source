package uk.ac.wellcome.sierra

import akka.NotUsed
import akka.stream.ThrottleMode
import akka.stream.scaladsl.Source
import io.circe.Json

import scala.concurrent.duration._

case class ThrottleRate(elements: Int, per: FiniteDuration, maximumBurst: Int)
case object ThrottleRate {
  def apply(elements: Int, per: FiniteDuration): ThrottleRate = ThrottleRate(elements, per, 0)
}

object SierraSource {
  private def buildSource(
    apiUrl: String,
    oauthKey: String,
    oauthSecret: String,
    maybeThrottleRate: Option[ThrottleRate]
  )(
    resourceType: String,
    params: Map[String, String]): Source[Json, NotUsed] = {
    val source = Source.fromGraph(
      new SierraPageSource(
        apiUrl = apiUrl,
        oauthKey = oauthKey,
        oauthSecret = oauthSecret)(
          resourceType = resourceType,
          params = params)
    )

    maybeThrottleRate match {
      case Some(throttleRate) =>
        source.throttle(
          throttleRate.elements,
          throttleRate.per,
          throttleRate.maximumBurst,
          ThrottleMode.shaping
        ).mapConcat(identity)
      case None => source
        .mapConcat(identity)
    }
  }

  def apply(
    apiUrl: String,
    oauthKey: String,
    oauthSecret: String,
    throttleRate: ThrottleRate
  )(
    resourceType: String,
    params: Map[String, String]): Source[Json, NotUsed] =
      buildSource(
        apiUrl = apiUrl,
        oauthKey = oauthKey,
        oauthSecret = oauthSecret,
        maybeThrottleRate = Some(throttleRate)
      )(
        resourceType = resourceType,
        params = params
      )

  def apply(
    apiUrl: String,
    oauthKey: String,
    oauthSecret: String
  )(
    resourceType: String,
    params: Map[String, String]): Source[Json, NotUsed] =
      buildSource(
        apiUrl = apiUrl,
        oauthKey = oauthKey,
        oauthSecret = oauthSecret,
        maybeThrottleRate = None
      )(
        resourceType = resourceType,
        params = params
      )
}
