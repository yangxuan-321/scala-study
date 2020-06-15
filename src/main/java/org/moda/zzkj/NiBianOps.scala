package org.moda.zzkj

object NiBianOps {
  def main(args: Array[String]): Unit = {
    val ducks = Array(new Duck("haha", "haha"), new Duck("heihei", "heihei"))
    showDuck(ducks)
  }

  // 逆变
  def showDuck[T >: PekingDuck](ducks: Array[T]): Unit ={
    ducks.foreach(duck => println(duck.getClass))
  }
}
