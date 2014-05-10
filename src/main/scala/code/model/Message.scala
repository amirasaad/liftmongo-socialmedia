package code.model

import net.liftweb.mongodb.record._
import field._
import net.liftweb.record.field._
import net.liftweb.util.FieldContainer
import code.lib.RogueMetaRecord


class Message private() extends MongoRecord[Message] with StringPk[Message] {
  override def meta = Message
  object user extends StringRefField(this, User, 128)
  object sender extends StringRefField(this, User, 128)
  object reciver extends StringRefField(this, User, 128)
  
  object msg extends StringField(this,2000)
  object time extends DateField(this)
}

object Message extends Message with MongoMetaRecord[Message]
