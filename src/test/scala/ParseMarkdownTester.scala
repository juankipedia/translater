/**
  * Created by marco on 02/06/17.
  */

import org.scalatest.WordSpec
import MarkdownTranslater._
/**
  * brief: Testing the parse from a Markdown JSON to an
  *   scala Map[String, Any]
  */
class ParseMarkdownSpec extends WordSpec{

  "ParseMarkdown " must {

    "Parse the addresBook.md file" in {
        /*Linux*/
        val myMap = ParseMarkdown.onlyMarkdown("./src/main/scala/MarkdownTranslater/addressBook.md")
        /*Windows*/
        //val myMap = ParseMarkdown.main(".\\src\\main\\scala\\MarkdownTranslater\\addresBook.md")
    }

    
    "Fail to read an un-format Markdown file" in {
      /*Linux*/
      intercept[Exception] {
        val myMap = ParseMarkdown.onlyMarkdown("./src/main/scala/MarkdownTranslater/addressBookWrong.md")
        /*Windows*/
        //val myMap = ParseMarkdown.main(".\\src\\main\\scala\\MarkdownTranslater\\addresBookWrong.md")
      }
    }

  }

}

/*
  *brief: testing the parse from a Markdown-JSON to a final JSON object
 */
class ParseMarkdownToJsonSpec extends WordSpec{

}