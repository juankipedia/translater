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
package MarkdownParser
import java.io.FileReader
import scala.util.parsing.combinator._

/**
  *brief: Main Program to prove Markdown Parser
  *params: strings arguments of program
  *date 30/05/2017
  */
object ParseMarkdown extends Markdown{
  def main(args: Array[String]){
    val map = parseMarkdown("\\Users\\Juan Diego\\Documents\\MAMMUT\\translater\\src\\main\\scala\\MarkdownParser\\addressBook.md")
    println(map.apply("\"address book\""))
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
  def member: Parser[(String,Any)] = "##"~>stringLiteral~":"~value ^^ {case name~":"~value => (name,value)}
  def value: Parser[Any] = (
    obj
      | arr
      | stringLiteral
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