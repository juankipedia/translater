package JSONserializer
import spray.json._
object MyJsonProtocol extends DefaultJsonProtocol {
  implicit object MapJsonFormat extends JsonFormat[Map[String, Any]] {
    def write(m: Map[String, Any]) = {
      JsObject(m.mapValues {
        case v: String => JsString(v)
        case v: Double => JsNumber(v)
        case v: Map[String, Any] => write(v)
        case v: List[String] => v.toJson
        case v: Any => JsString(v.toString)
      })
    }
    def read(value: JsValue) = ???
  }
}