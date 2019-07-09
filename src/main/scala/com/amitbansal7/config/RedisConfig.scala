package com.amitbansal7.config
import com.redis._

object RedisConfig {
  private val redisClient = new RedisClient("localhost", 6379)

  def getRedisClient = redisClient
}