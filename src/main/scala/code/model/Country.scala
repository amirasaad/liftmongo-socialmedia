package code.model

import net.liftweb.mongodb.record._
import field._
import net.liftweb.record.field._
import net.liftweb.util.FieldContainer
import code.lib.RogueMetaRecord


class Planet private() extends MongoRecord[Planet] with StringPk[Planet] {
	override def meta = Planet
	object review extends StringField(this,1024)
}
object Planet extends Planet with MongoMetaRecord[Planet] {
	override def collectionName = "example.planet"
}
class Country private () extends MongoRecord[Country] with StringPk[Country] {
	override def meta = Country
	object planet extends StringRefField(this, Planet, 128)
}
object Country extends Country with MongoMetaRecord[Country] {
	override def collectionName = "example.country"
}