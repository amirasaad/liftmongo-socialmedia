package code.snippet

import code.model._

import net.liftweb._
import common._
import http._
import js._
import JsCmds._
import SHtml._
import scala.xml._

import util.Helpers._
import org.bson.types.ObjectId


import code.comet.PostsServer

object PostForm {
  var post = Activity.createRecord
  
  def add = {

    def process(): JsCmd = {
      var tempUser = User.currentUser
      post.ownerId.set(new ObjectId(tempUser.get.userIdAsString))
      post.validate match {
        case Nil => {
          post.save
          S.notice("Post created")
          PostsServer ! post
          post = Activity.createRecord
          tempUser = User.currentUser
          SetValById("body", "")
        }
        case errors => S.error("Fail " + errors.flatMap(_.msg).head)

      }
    }
    "name=body" #> (SHtml.textarea(post.body.get, post.body.set(_), "id" -> "body",
      "placeholder" -> ("What is in your mind, " + User.currentUser.get.name.get + "?"),
      "class" -> "form-control") ++
      SHtml.hidden(process))
  }
  def initComet = {
    PostsServer ! post
    "*" #> "Loading...."
  }
}

