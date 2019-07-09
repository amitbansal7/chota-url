package com.amitbansal7.repositories.mongo

import com.amitbansal7.config.MongoConfig._
import com.amitbansal7.models.ShortUrl
import org.mongodb.scala.bson.collection.immutable.Document
import org.mongodb.scala._
import org.mongodb.scala.model.Updates._
import org.mongodb.scala.model.Filters._

import scala.concurrent.Future

class ShortUrlMongo {

  private val shortUrlCollection = getShortUrlCollection()

  def getByOriginalUrl(originalUrl: String): Future[ShortUrl] =
    shortUrlCollection
      .find(Document("oUrl" -> originalUrl))
      .first().toFuture()

  def getByShortUrl(shortUrl: String): Future[ShortUrl] =
    shortUrlCollection
      .find(Document("sUrl" -> shortUrl))
      .first().toFuture()

  def addUrl(url: ShortUrl) =
    shortUrlCollection.insertOne(url).toFuture()

  def incrementHit(sUrl: String) =
    shortUrlCollection.updateOne(
      equal("sUrl", sUrl),
      inc("hits", 1)).toFuture()
}