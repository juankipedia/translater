import spray.json._

case class Color(name: String, red: Int, green: Int, blue: Int)

object MyJsonProtocol extends DefaultJsonProtocol {
  implicit val colorFormat = jsonFormat4(Color)
}

import MyJsonProtocol._
import spray.json._

val json = Color("CadetBlue", 95, 158, 160).toJson

val color = json.convertTo[Color]
