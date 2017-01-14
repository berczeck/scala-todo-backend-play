package models

import scala.collection.mutable.ArrayBuffer

/**
  * Created by HP on 13/01/2017.
  */
class TodoRepository {
  def getAll: Array[TodoModel] = TodoRepository.todoList.toArray

  def get(id: Int): Option[TodoModel] = {
    TodoRepository.todoList.find(_.id == id)
  }

  def save(model: TodoModel): TodoModel = {
    model.id match {
      case 0 =>
        TodoRepository.maxId += 1
        val todo = model.copy(id = TodoRepository.maxId)
        TodoRepository.todoList += todo
        todo
      case _ =>
        val index = TodoRepository.todoList.indexOf(model)
        index match {
          case x:Int if x >= 0 =>
            TodoRepository.todoList(index) = model
          case _ =>
            TodoRepository.todoList += model
        }
        model
    }
  }

  def delete(): Unit = TodoRepository.todoList.clear()

  def delete(id: Int): Unit = {
    TodoRepository.todoList.filter(_.id == id).headOption match {
      case Some(x) => TodoRepository.todoList.remove(TodoRepository.todoList.indexOf(x))
      case None =>
    }
  }
}

object TodoRepository {
  private var maxId: Int = 0
  //http://alvinalexander.com/scala/scala-mutable-arrays-adding-elements-to-arrays
  private var todoList: ArrayBuffer[TodoModel] = ArrayBuffer[TodoModel]()
}