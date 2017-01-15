package controllers

import akka.util.ByteString
import models.{TodoModel, TodoRepository}
import play.api.http.HttpEntity
import play.api.libs.json.{JsError, JsSuccess, Json}
import play.api.mvc._

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

  def post() = Action { request =>
    //http://alvinalexander.com/scala/how-to-write-play-framework-http-post-request-json-web-service
    val json = request.body.asJson.get

    //https://www.playframework.com/documentation/2.5.x/ScalaJson
    json.validate[TodoModel] match {
      case s: JsSuccess[TodoModel] =>
        val todo = s.get.copy(url = s"${request.path}")
        val model: TodoModel = repo.save(todo)
        Result(
          header = ResponseHeader(200, Map("location" -> model.url)),
          body = HttpEntity.Strict(ByteString(Json.toJson(model).toString()), Some("application/json"))
        )
      case _: JsError =>
        BadRequest("Bad request")
    }
  }

  def patch(id:Int) = Action { request =>
    val json = request.body.asJson.get
    val todo: TodoModel = json.as[TodoModel].copy(id = id)
    val model: TodoModel = repo.save(todo)
    Result(
      header = ResponseHeader(200, Map("location" -> model.url)),
      body = HttpEntity.Strict(ByteString("updated!"), Some("text/plain"))
    )
  }

  def deleteAll() = Action {
    repo.delete()
    Ok("List deleted")
  }

  def delete(id:Int) = Action {
    repo.delete(id)
    Ok(s"Element $id deleted")
  }

}
