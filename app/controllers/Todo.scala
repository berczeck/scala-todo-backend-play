package controllers

import models.{TodoModel, TodoRepository}
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}

/**
  * Created by HP on 13/01/2017.
  */
class Todo extends Controller {
  val repo: TodoRepository = new TodoRepository()

  def getAll = Action {
    Ok(Json.toJson(repo.getAll))
  }

  def get(id:Int) = Action {
    Ok(Json.toJson(repo.get(id)))
  }

  def post() = ???

  def patch() = ???

  def deleteAll() = Action {
    repo.delete()
    Ok("List deleted")
  }

  def delete(id:Int) = Action {
    repo.delete(id)
    Ok(s"Element $id deleted")
  }

}
