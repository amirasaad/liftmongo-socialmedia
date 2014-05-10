package code.comet

import net.liftweb._
import http._
import util._
import Helpers._
import code.model._
import net.liftmodules.extras.Gravatar
import code.lib.TimeHelper

class PostActor extends CometActor with CometListener {
  private var posts = List[Activity]()

  def registerWith = PostsServer

  override def lowPriority = {
    case p: List[Activity] => posts = p; reRender
  }

  def render = {
    ".count *" #> posts.length &
    ".posts *" #> posts.map { p =>
      ".body" #> p.body.get &
      ".time" #> TimeHelper.format(p.at.get)&
      ".username" #>  p.ownerId.obj.get.username.get &
      "a [href]" #> ("user/"+p.ownerId.obj.get.username.get) &
      ".img" #> Gravatar.imgTag(p.ownerId.obj.get.email.get, 64)
    }
  }
}


 /* 
     ".count *" #> posts.length &
    ".posts *" #> posts.map { p =>
      ".post *" #> Unparsed(p.body.get.replaceAll("\n", "<br>")) &
        ".time *" #> TimeHelpers.hourFormat(p.at.get) &
        ".user" #> p.ownerId.obj.map ({ u =>
          ".username *" #> u.username.get &
            ".avatar *" #> Gravatar.imgTag(u.email.get, 64)

        })
        }

        ".img *" #> Gravatar.imgTag( p.ownerId.obj.map(_.email.get).get, 64)


          ".username" #>  p.ownerId.obj.get.username &
        ".avatar" #> Gravatar.imgTag(p.ownerId.obj.get.email.get, 64)
        */


      //  ".username *" #> p.user.obj.get.username
      //  ".avatar *" #> Gravatar.imgTag( p.user.obj.get.email.get, 64)
