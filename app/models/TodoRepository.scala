package models

/**
  * Created by HP on 13/01/2017.
  */
class TodoRepository {
  def getAll(): Array[Todo] = TodoRepository.todoList

  def get(id: Int): Todo = TodoRepository.todoList.filter(_.id == id).head

  def save(model: Todo): Todo = ???

  def delete(): Unit = TodoRepository.todoList = Array[Todo]()

  def delete(id: Int): Unit = TodoRepository.todoList.dropWhile(_.id == id)
}

object TodoRepository {
  private var maxId: Int = 0
  private var todoList: Array[Todo] = _
}