package models

import play.api.libs.json.{Json, Writes, Reads}

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
  implicit val jsonReads: Reads[TodoModel] = Json.reads[TodoModel]

  implicit val todoWrites = new Writes[TodoModel] {
    def writes(model: TodoModel) = Json.obj(
      "id" -> model.id,
      "url" -> model.url,
      "order" -> model.order,
      "title" -> model.title,
      "completed" -> model.completed
    )
  }
}
