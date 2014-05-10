package code.comet

import net.liftweb._
import http._
import actor._
import code.model._

object  PostsServer extends LiftActor with ListenerManager {
  private var posts = List[Activity]()
  
  def createUpdate = posts
  
  override def lowPriority = {
    case p:Activity => {
      posts = Activity.findAll.reverse.take(100)
      updateListeners()
    }
  }
}