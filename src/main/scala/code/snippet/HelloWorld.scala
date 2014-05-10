package code
package snippet

import code.lib._
import code.model._

import java.util.Date
import scala.xml.{NodeSeq, Text}

import net.liftweb._
import common._
import util._
import Helpers._

import net.liftmodules.extras.Gravatar

class HelloWorld {
  lazy val date: Box[Date] = DependencyFactory.inject[Date] // inject the date

  // replace the contents of the element with id "time" with the date
  def render = {
  	"#time *" #> date.map(_.toString) &
  	"#avatar *" #> Gravatar.imgTag("test@nowhere.com")
  }

  val uk = Country.find("uk") openOr {
  	val earth = Planet.createRecord.id("earth").review("Harmless").save
  	Country.createRecord.id("uk").planet(earth.id.is).save
  }
  def facts =
  ".country *" #> uk.id &
  ".planet" #> uk.planet.obj.map { p =>
  	".name *" #> p.id &
  	".review *" #> p.review
  }
}

