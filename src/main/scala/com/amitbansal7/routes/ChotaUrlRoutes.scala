package com.amitbansal7.routes

import com.amitbansal7.services.ChotaUrlService
import com.amitbansal7.Modules._
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives._
import com.amitbansal7.config.JsonSupport._

class ChotaUrlRoutes(chotaUrlService: ChotaUrlService) {
  def routes = {
    (path("shorten") & post) {
      parameter('url) { (url) =>
        extractHost { host =>
          complete(StatusCodes.OK, chotaUrlService.shortenUrl(url, host))
        }
      }
    } ~ path("(\\w+)\\+$".r) { url =>
      get {
        extractHost { host =>
          complete(chotaUrlService.getSUrlInfo(url, host))
        }
      }
    } ~ path("(\\w+)$".r) { url =>
      get {
        chotaUrlService.getOriginalUrl(url)
      }
    }
  }
}