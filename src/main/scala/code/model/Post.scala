package code.model

import net.liftweb.mongodb.record._
import field._
import net.liftweb.record.field._
import net.liftweb.util.FieldContainer
import code.lib.RogueMetaRecord

class Activity private () extends MongoRecord[Activity] with ObjectIdPk[Activity] {
  override def meta = Activity
  object body extends StringField(this, 2000) {
    override def displayName = "Post"

    override def validations =
      valMaxLen(2000, "Post must be 2000 characters or less") _ ::
        valMinLen(3, "Post must be 3 characters or more") _ ::
        super.validations
  }
  object ownerId extends ObjectIdRefField(this, User)
  object at extends DateField(this)

  def postScreenFields = new FieldContainer {
    def allFields = List(body)
  }

}
object Activity extends Activity with MongoMetaRecord[Activity] with RogueMetaRecord[Activity] {

}
