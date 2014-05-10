package code.snippet

import net.liftweb.common.Empty
import net.liftweb.util.Helpers._
import net.liftweb.http.SHtml._
import net.liftweb.http.js.JsCmd
import net.liftweb.http.js.JsCmds.SetHtml
import xml.Text
import net.liftweb.http.S
import net.liftweb.http.SHtml


class HtmlSelectSnippet {
	// Our "database" maps planet names to distances:
	type Planet = String
	type LightYears = Double
	val database = Map[Planet,LightYears](
		"Alpha Centauri Bb" -> 4.23,
		"Tau Ceti e" -> 11.90,
		"Tau Ceti f" -> 11.90,
		"Gliese 876 d" -> 15.00,
		"82 G Eridani b" -> 19.71
		)
	def render = {
		// To show the user a blank label and blank value option:
		val blankOption = ("" -> "")
		// The complete list of options includes everything in our database:
		val options : List[(String,String)] =
		blankOption ::
		database.keys.map(p => (p,p)).toList
        // Nothing is selected by default:
        val default = Empty
        // The function to call when an option is picked:
        def handler(selected: String) : JsCmd = {
		  SetHtml("info",Text("Distance: "+database(selected) + " light years " + "Cost:" + database(selected)*10 + "$"))

        }
        // Bind the <select> tag:
        var selectedValue : String = ""
        "select" #> onEvents("onchange")(handler) {
        	select(options, default, selectedValue = _)
        	} &
        "type=submit" #> onSubmitUnit( () => S.notice("Destination "+selectedValue))
    }
}