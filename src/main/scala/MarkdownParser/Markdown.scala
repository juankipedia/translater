/**
  *file: Markdown.scala
  *
  *brief: Parser for objects formatted with Markdown, Markdown text should has some
          rules:
            -Objects are members separated by "." and that are among expressions
             " # ObjectDefinition " and " # EndObjectDefinition "
            -Arrays are values separated by "," and that are among "*"
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
object ParseJSON extends Markdown {
  def main(args: Array[String]){
    //  render = new FileReader(args(0))
    println(parseAll(value,"# ObjectDefinition\n  ## \"address book\":\n       # ObjectDefinition\n            ## \"name\":\n                \"john smith\".\n            ## \"address\":\n                # ObjectDefinition\n                  ## \"street\":\n                    \"10 Market street\".\n                  ## \"city\":\n                    \"San Francisco, CA\".\n                  ## \"zip\":\n                    94111\n                # EndObjectDefinition .\n            ## \"phone numbers\":\n            *\"04140802832\",\n             \"04149798710\"*\n      # EndObjectDefinition .\n  ## \"value\": 333\n# EndObjectDefinition"))
  }
}

/**
  *brief: Markdown parser class that extends from JavaTokenParsers
  *date 30/05/2017
  */
class Markdown extends JavaTokenParsers{
  def obj: Parser[Map[String, Any]] = "# ObjectDefinition"~>repsep(member, ".")<~"# EndObjectDefinition" ^^ {Map() ++ _}
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
}
