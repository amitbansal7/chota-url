package com.amitbansal7.models

import org.mongodb.scala.bson.ObjectId

object ShortUrl {
  def apply(
    _id: ObjectId,
    sUrl: String, // Short url
    oUrl: String, //Original url
    hits: Int, // visit count
    ts: Long): ShortUrl = new ShortUrl(_id, sUrl, oUrl, hits, ts)

  def apply(
    sUrl: String, // Short url
    oUrl: String, //Original url
    hits: Int, // visit count
    ts: Long): ShortUrl = new ShortUrl(new ObjectId(), sUrl, oUrl, hits, ts)

}

case class ShortUrl(
  _id: ObjectId,
  sUrl: String, // Short url
  oUrl: String, //Original url
  hits: Int, // Visit count
  ts: Long)