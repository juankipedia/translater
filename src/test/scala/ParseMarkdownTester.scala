/**
  * Created by marco on 02/06/17.
  */

import org.scalatest.WordSpec
import MarkdownTranslater._
/**
  * brief: Testing the parse from a Markdown to an
  *   scala Map[String, Any] to an JSON
  */
class ParseMarkdownSpec extends WordSpec {

  "ParseMarkdown " when {

    "Reads the addresMarkdown.md file" should {

      "Parse the representation of the object to a Map" in {
        /*Linux*/
        ParseMarkdown.onlyMarkdown("./src/main/scala/MarkdownTranslater/addressBook.md")
        /*Windows*/
        //ParseMarkdown.onlyMarkdown(".\\src\\main\\scala\\MarkdownTranslater\\addressBook.md")
      }

      "Parse the addressBook.md file to a JSON usin SprayJSON" in {
        /*Linux*/
        ParseMarkdown.markdownToJson("./src/main/scala/MarkdownTranslater/addressBook.md")
        /*Windows*/
        //ParseMarkdown.markdownToJson(".\\src\\main\\scala\\MarkdownTranslater\\addressBook.md")
      }

      "Return a  Map" that {

        "is non-empty" in {
          /*Linux*/
          val myMap = ParseMarkdown.onlyMarkdown("./src/main/scala/MarkdownTranslater/addressBook.md")
          /*Windows*/
          //def myMap = ParseMarkdown.onlyMarkdown(".\\src\\main\\scala\\MarkdownTranslater\\addressBook.md")
          assert(myMap.isEmpty === false)
        }

        "Contains the key: \"address book\"" in {
          /*Linux*/
          val myMap = ParseMarkdown.onlyMarkdown("./src/main/scala/MarkdownTranslater/addressBook.md")
          /*Windows*/
          //def myMap = ParseMarkdown.onlyMarkdown(".\\src\\main\\scala\\MarkdownTranslater\\addressBook.md")
          assert(myMap.contains("address book"))
        }

        "Contains the key \"333\"" in {
          /*Linux*/
          val myMap = ParseMarkdown.onlyMarkdown("./src/main/scala/MarkdownTranslater/addressBook.md")
          /*Windows*/
          //def myMap = ParseMarkdown.onlyMarkdown(".\\src\\main\\scala\\MarkdownTranslater\\addressBook.md")
          assert(myMap("value").toString === "333.0")
        }
      }

      "Reads an un-format Markdown file" should {

        "fail" in {
          intercept[Exception] {
            /*Linux*/
            //ParseMarkdown.onlyMarkdown("./src/main/scala/MarkdownTranslater/addressBookWrong.md")
            /*Windows*/
            ParseMarkdown.onlyMarkdown(".\\src\\main\\scala\\MarkdownTranslater\\addressBookWrong.md")
          }
        }

      }
    }
  }
}





