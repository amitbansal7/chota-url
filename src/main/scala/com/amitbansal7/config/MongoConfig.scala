package com.amitbansal7.config
import com.amitbansal7.models.ShortUrl

import org.mongodb.scala._
import org.mongodb.scala.bson.codecs.Macros._
import org.mongodb.scala.bson.codecs.DEFAULT_CODEC_REGISTRY
import org.bson.codecs.configuration.CodecRegistries.{ fromProviders, fromRegistries }

object MongoConfig {
  val mongoClient = MongoClient()

  private val shortUrlCodecRegisty = fromRegistries(fromProviders(classOf[ShortUrl]), DEFAULT_CODEC_REGISTRY)

  val db = mongoClient
    .getDatabase("chotaUrl")
    .withCodecRegistry(shortUrlCodecRegisty)

  private val shortUrlCollection: MongoCollection[ShortUrl] = db.getCollection("shortUrl")

  def getShortUrlCollection() = shortUrlCollection

}