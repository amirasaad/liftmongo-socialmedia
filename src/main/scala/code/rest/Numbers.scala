package code.rest

import net.liftweb.http.{ Req, OutputStreamResponse }
import net.liftweb.http.rest._

object Numbers extends RestHelper {
  // Convert a number to a String, and then to UTF-8 bytes
  // to send down the output stream.
  def num2bytes(x: Int) = (x + "\n") getBytes ("utf-8")
  // Generate numbers using a Scala stream:
  def infinite = Stream.from(1).map(num2bytes)
  serve {
    case Req("numbers" :: Nil, _, _) =>
      OutputStreamResponse(out => infinite.foreach(out.write))
  }
}