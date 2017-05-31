package MarkdownParser
import java.io.FileReader

import scala.util.parsing.combinator._
import java.io.FileReader._
/**
  * Created by Juan Diego on 5/30/2017.
  */

class JSON extends JavaTokenParsers {
  def obj: Parser[Map[String, Any]] = "{"~>repsep(member, ",")<~"}" ^^ {Map() ++ _}
  def arr: Parser[List[Any]] = "["~>repsep(value,",")<~"]"
  def member: Parser[(String,Any)] = stringLiteral~":"~value ^^ {case name~":"~value => (name,value)}
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

object ParseJSON extends Markdown {
  def main(args: Array[String]){
    //  render = new FileReader(args(0))
    println(parseAll(value,"# ObjectDefinition\n  ## \"address book\":\n       # ObjectDefinition\n            ## \"name\":\n                \"john smith\".\n            ## \"address\":\n                # ObjectDefinition\n                  ## \"street\":\n                    \"10 Market street\".\n                  ## \"city\":\n                    \"San Francisco, CA\".\n                  ## \"zip\":\n                    94111\n                |\n            .\n            ## \"phone numbers\":\n            *\"04140802832\",\n             \"04149798710\"*\n      |\n  .\n  ## \"value\": 333\n|"))
  }
}

class Markdown extends JavaTokenParsers{
  def obj: Parser[Map[String, Any]] = "# ObjectDefinition"~>repsep(member, ".")<~"|" ^^ {Map() ++ _}
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
