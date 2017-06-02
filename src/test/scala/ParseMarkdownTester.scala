/**
  * Created by marco on 02/06/17.
  */

import org.scalatest.WordSpec
import MarkdownTranslater._
/**
  * brief: Testing the parse from a Markdown to an
  *   scala Map[String, Any] to an JSON
  */
class ParseMarkdownSpec extends WordSpec{

  "ParseMarkdown " must {

    "Parse the addressBook.md file to an Map" in {
        /*Linux*/
        ParseMarkdown.onlyMarkdown("./src/main/scala/MarkdownTranslater/addressBook.md")
        /*Windows*/
        //ParseMarkdown.main(".\\src\\main\\scala\\MarkdownTranslater\\addressBook.md")
    }

    
    "Fail to read an un-format Markdown file" in {
      /*Linux*/
      intercept[Exception] {
        ParseMarkdown.onlyMarkdown("./src/main/scala/MarkdownTranslater/addressBookWrong.md")
        /*Windows*/
        //ParseMarkdown.main(".\\src\\main\\scala\\MarkdownTranslater\\addressBookWrong.md")
      }
    }

    "Parse the addressBook.md file to a JSON usin SprayJSON" in{
      /*Linux*/
      ParseMarkdown.markdownToJson("./src/main/scala/MarkdownTranslater/addressBook.md")
      /*Windows*/
      //ParseMarkdown.markdownToJson(".\\src\\main\\scala\\MarkdownTranslater\\addressBook.md")
    }

  }

}

