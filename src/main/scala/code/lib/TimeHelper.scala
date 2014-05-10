package code.lib

import java.util.Date
import java.text.SimpleDateFormat

object TimeHelper {
  def format(d: Date) = d match {
    case null => ""
    case _ =>   new SimpleDateFormat("EEE, d MMM yy HH:mm:ss").format(d)
  }

}