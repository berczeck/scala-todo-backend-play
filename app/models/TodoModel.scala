package models

import play.api.libs.json.{Json, Writes}

/**
  * Created by HP on 13/01/2017.
  */
case class TodoModel(id:Int, order:Int, title:String, url:String, completed:Boolean) {
  override def equals(obj: Any): Boolean = {
    obj match {
      case x: TodoModel if x.id == this.id => true
      case _ => false
    }
  }

  override def hashCode(): Int = id.hashCode()
}

object TodoModel {
  implicit val jsonWrites: Writes[TodoModel] = Json.writes[TodoModel]
}