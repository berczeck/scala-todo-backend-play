package models

/**
  * Created by HP on 13/01/2017.
  */
class TodoRepository {
  def getAll: Array[TodoModel] = TodoRepository.todoList

  def get(id: Int): Option[TodoModel] = {
    TodoRepository.todoList.find(_.id == id)
  }

  def save(model: TodoModel): TodoModel = {
    model.id match {
      case 0 =>
        TodoRepository.maxId += 1
        val todo = model.copy(id = TodoRepository.maxId)
        TodoRepository.todoList :+ todo
        todo
      case _ =>
        val index = TodoRepository.todoList.indexOf(model)
        index match {
          case x:Int if x >= 0 =>
            TodoRepository.todoList(index) = model
          case _ =>
            TodoRepository.todoList :+ model
        }
        model
    }
  }

  def delete(): Unit = TodoRepository.todoList = Array[TodoModel]()

  def delete(id: Int): Unit = TodoRepository.todoList.dropWhile(_.id == id)
}

object TodoRepository {
  private var maxId: Int = 0
  private var todoList: Array[TodoModel] = Array[TodoModel]()
}