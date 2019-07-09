package com.amitbansal7.services

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives.{ complete, redirect }
import com.amitbansal7.Modules._
import com.amitbansal7.models.ShortUrl
import com.amitbansal7.repositories.mongo.ShortUrlMongo
import com.amitbansal7.repositories.redis.ShortUrlRedis
import org.apache.commons.lang3.RandomStringUtils
import com.amitbansal7.models.ShortUrl
import org.mongodb.scala.bson.ObjectId
import scala.concurrent.ExecutionContext.Implicits.global
import com.amitbansal7.config.JsonSupport._

import scala.concurrent.Future

object ChotaUrlService {
  case class URL(oUrl: String, sUrl: String)
  case class UrlInfoRes(original: String, short: String, hits: Int, creationTime: Long)
}

class ChotaUrlService(shortUrlRedisRepository: ShortUrlRedis, shortUrlMongoRepository: ShortUrlMongo) {

  import ChotaUrlService._
  def getOriginalUrl(shortUrl: String) = {
    shortUrlRedisRepository.getUrl(shortUrl) match {
      case Some(url) =>
        shortUrlMongoRepository.incrementHit(shortUrl)
        redirect(url, StatusCodes.MovedPermanently)
      case _ => complete(StatusCodes.NotFound)
    }
  }

  def getSUrlInfo(sUrl: String, host: String) = {
    shortUrlMongoRepository.getByShortUrl(sUrl) map {
      case url: ShortUrl =>
        Some(UrlInfoRes(url.oUrl, host + "/" + url.sUrl, url.hits, url.ts))
      case _ => None
    }
  }

  def generateUniqueCode(url: String) = {
    RandomStringUtils.random(8, true, true)
  }

  def shortenUrl(oUrl: String, host: String): Future[URL] =
    shortUrlMongoRepository.getByOriginalUrl(oUrl) map {
      //if Url is already shortened.
      case url: ShortUrl =>
        URL(url.oUrl, url.sUrl)

      //New url found
      case _ =>
        val shortCode = generateUniqueCode(oUrl);
        val shortUrl = host + "/" + shortCode
        shortUrlMongoRepository.addUrl(ShortUrl(new ObjectId(), shortCode, oUrl, 0, System.currentTimeMillis()))
        shortUrlRedisRepository.setUrl(shortCode, oUrl)
        URL(oUrl, shortUrl)
    }
}