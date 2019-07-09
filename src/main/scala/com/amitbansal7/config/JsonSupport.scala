package com.amitbansal7.config

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import com.amitbansal7.models.ShortUrl
import com.amitbansal7.services.ChotaUrlService.{ URL, UrlInfoRes }
import org.mongodb.scala.bson.ObjectId
import spray.json.{ DefaultJsonProtocol, JsString, JsValue, RootJsonFormat }

object JsonSupport extends SprayJsonSupport with DefaultJsonProtocol {

  implicit object ObjectIdSerializer extends RootJsonFormat[ObjectId] {
    override def read(json: JsValue): ObjectId = new ObjectId(json.toString)

    override def write(obj: ObjectId): JsValue = JsString(obj.toHexString)
  }

  implicit val shortUrlFormat = jsonFormat5(ShortUrl.apply)
  implicit val URLresFormat = jsonFormat2(URL)
  implicit val ulInfoResFormat = jsonFormat4(UrlInfoRes)

}