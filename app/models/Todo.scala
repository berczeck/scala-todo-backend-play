package models

/**
  * Created by HP on 13/01/2017.
  */
case class Todo(id:Int,order:Int,title:String,url:String,completed:Boolean) {
  override def equals(obj: Any): Boolean = {
    obj match {
      case x: Todo if x.id == this.id => true
      case _ => false
    }
  }

  override def hashCode(): Int = id.hashCode()
}
