package com.amitbansal7.repositories.redis

import com.amitbansal7.config.RedisConfig

class ShortUrlRedis {
  val redisClient = RedisConfig.getRedisClient

  def setUrl(shortUrl: String, originalUrl: String) =
    redisClient.set(shortUrl, originalUrl)

  def getUrl(shortUrl: String) =
    redisClient.get(shortUrl)
}