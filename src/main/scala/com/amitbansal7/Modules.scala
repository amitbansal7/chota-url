package com.amitbansal7

import com.amitbansal7.repositories.mongo.ShortUrlMongo
import com.amitbansal7.repositories.redis.ShortUrlRedis
import com.amitbansal7.routes.ChotaUrlRoutes
import com.amitbansal7.services.ChotaUrlService
import com.softwaremill.macwire._

object Modules {

  val shortUrlRedisRepository = wire[ShortUrlRedis]
  val shortUrlMongoRepository = wire[ShortUrlMongo]
  val chotaUrlService = wire[ChotaUrlService]
  val chotaUrlRoutes = wire[ChotaUrlRoutes]

}