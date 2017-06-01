/**
  *file: Markdown.scala
  *
  *brief: Parser for objects formatted with Markdown, Markdown text should has some
          rules:
            -Objects are members separated by "." and that are among expressions
             " # ObjectDefinition " and " # EndObjectDefinition "
            -Arrays are values separated by "," and they are among "*"
            -Members are Strings and values separated by ":", and begins with "##"
            -Values are Objects or Arrays, Strings, Floating point number,"null","true"
             or "false"
  *
  *version 1.0.
  *date 30/05/2017
  */
//Package definitions and imports
package MarkdownTranslater
import java.io.FileReader
import scala.util.parsing.combinator._

/**
  *brief: Object to create a new JSON protocol, function "toJson"
          is now going to be able to take an object of class
          Map[String, Any] is going to be serialize to a JSON object
  *date 30/05/2017
  */
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
import spray.json._
import MyJsonProtocol._

/**
  *brief: Main Program to prove Markdown Parser
  *params: strings arguments of program
  *date 30/05/2017
  */
object ParseMarkdown extends Markdown{
  def main(args: Array[String]){
    val map = parseMarkdown("\\Users\\Juan Diego\\Documents\\MAMMUT\\translater\\src\\main\\scala\\MarkdownTranslater\\addressBook.md")
    println(map.apply("address book"))
    println(map.toJson)
  }
}
/**
  *brief: Markdown parser class that extends from JavaTokenParsers
  *date 30/05/2017
  */
class Markdown extends JavaTokenParsers{
  def obj: Parser[Map[String, Any]] = "# ObjectDefinition"~>repsep(member, ".")<~"# EndObjectDefinition" ^^
    {case members => members.toMap}
  def arr: Parser[List[Any]] = "*"~>repsep(value,",")<~"*"
  def member: Parser[(String,Any)] = "##"~>"[a-zA-Z_][a-zA-Z0-9_ ]*".r~":"~value ^^ {case name~":"~value => (name,value)}
  def value: Parser[Any] = (
    obj
      | arr
      | stringLiteral ^^ {str => str.substring(1, str.length - 1)}
      | floatingPointNumber ^^ (_.toDouble)
      | "null" ^^ (x => null)
      | "true" ^^ (x => true)
      | "false" ^^ (x => false)
    )
  def parseMarkdown(file: String): Map[String,Any] = {
    val retMap = parseAll(obj, new FileReader(file))
    println(retMap)
    retMap match {
      case Success(result, next) =>
        result
    }
  }
}