package code.comet

import net.liftweb._
import http._
import SHtml._
import net.liftweb.common.{ Box, Full }
import net.liftweb.util._
import net.liftweb.actor._
import net.liftweb.util.Helpers._
import net.liftweb.http.js.JsCmds.{ SetHtml }
import scala.xml.Text
import java.text.SimpleDateFormat


class CometClock extends CometActor {

  override def defaultPrefix = Full("comet")

  def render = "time" #> <span id="time">Whatever you feel like returning</span>

  Schedule.schedule(this, Clock, 10000L)
  
  val df = new SimpleDateFormat("dd MMM YYYY hh:mm:ss")

  override def lowPriority: PartialFunction[Any, Unit] = {
    case Clock => {
      partialUpdate(SetHtml("time", Text(df.format(now))))
      Schedule.schedule(this, Clock, 10000L)
    }
  }
}


case object Clock
